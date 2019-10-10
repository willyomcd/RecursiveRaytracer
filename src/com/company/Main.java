package com.company;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.MathArrays;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.apache.commons.math3.util.MathArrays.checkPositive;
import static org.apache.commons.math3.util.MathArrays.ebeMultiply;

public class Main {

    public static void main(String[] args) {
        ArrayList<Light> lights = new ArrayList();
        ArrayList<Sphere> spheres = new ArrayList();
        double [] ambience = new double[3];
        Camera camera = new Camera();
        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next();
                switch (word) {
                    case "eye":
                        double ex = scanner.nextDouble();
                        double ey = scanner.nextDouble();
                        double ez = scanner.nextDouble();
                        String look = scanner.next();
                        double lx =scanner.nextDouble();
                        double ly =scanner.nextDouble();
                        double lz =scanner.nextDouble() ;
                        String up = scanner.next();
                        double ux =scanner.nextDouble();
                        double uy = scanner.nextDouble();
                        double uz = scanner.nextDouble();
                        String d = scanner.next();
                        double dValue = scanner.nextDouble();
                        String bounds = scanner.next();
                        double bLeft  = scanner.nextDouble();
                        double bRight  = scanner.nextDouble();
                        double bBottum  = scanner.nextDouble();
                        double bTop  = scanner.nextDouble();
                        String res = scanner.next();
                        int width = scanner.nextInt();
                        int height = scanner.nextInt();
                        camera = new Camera(ex,ey,ez,lx,ly,lz,ux,uy,uz,dValue,bLeft,bRight,bBottum,bTop,width,height);
                        //System.out.println(camera.toString());
                        break;
                    case "ambient":
                        ambience[0] = scanner.nextDouble();
                        ambience[1]  = scanner.nextDouble();
                        ambience[2] = scanner.nextDouble();

                        break;
                    case "light":
                        double lightx   =scanner.nextDouble();
                        double lighty = scanner.nextDouble();
                        double lightz = scanner.nextDouble();
                        double w =  scanner.nextDouble();
                        double red  = scanner.nextDouble();
                        double green = scanner.nextDouble();
                        double blue = scanner.nextDouble();
                        Light light = new Light(lightx,lighty,lightz,w, red, green, blue);
                        //System.out.println(light.toString());
                        lights.add(light);
                        break;
                    case "sphere":
                        double sx = scanner.nextDouble();
                        double sy = scanner.nextDouble();
                        double sz = scanner.nextDouble();
                        double radius = scanner.nextDouble();
                        double ambientRed= scanner.nextDouble();
                        double ambientGreen = scanner.nextDouble();
                        double ambientBlue= scanner.nextDouble();
                        double  diffuseRed = scanner.nextDouble();
                        double  diffuseGreen= scanner.nextDouble();
                        double  diffuseBlue= scanner.nextDouble();
                        double  specRed= scanner.nextDouble();
                        double  specGreen= scanner.nextDouble();
                        double  specBlue= scanner.nextDouble();
                        double  attenRed = scanner.nextDouble();
                        double  attenGreen= scanner.nextDouble();
                        double attenBlue = scanner.nextDouble();
                        Sphere sphere = new Sphere(sx,sy,sz,radius,ambientRed,ambientBlue,ambientGreen,diffuseRed,diffuseGreen,diffuseBlue,
                                specRed,specGreen,specBlue,attenRed,attenGreen,attenBlue);
                       // System.out.println(sphere.toString());
                        spheres.add(sphere);
                }


            }
            scanner.close();
           int [][][]image = render(camera,lights,spheres, ambience);
            outputFile(image,args[0]);
        } catch (IOException e) {
            System.exit(1);
        }
    }
    public static int[][][] render(Camera camera, ArrayList<Light> lights,ArrayList<Sphere> spheres, double[] ambience){
        int [][][] image = new int[camera.getHeight()][camera.getWidth()][3];

            for(int i = 0; i <camera.getWidth(); i++) {
                for(int j = 0; j< camera.getHeight(); j++) {
                    Vector3D [] ray = new Vector3D[2];
                    ray = makeRay(i,j,camera);
                    int closeSphere = -1;
                    closeSphere = findCloseSphere(ray,spheres,camera);
                    double [] color = colorRay(ray, spheres.get(closeSphere),ambience, lights);
                    int[] pixelValues = roundToInt(color);
                    image [j][i][0] = pixelValues[0];
                    image [j][i][1] = pixelValues[1];
                    image [j][i][2] = pixelValues[2];
                    //System.out.println("colors" + Arrays.toString(pixelValues));

                }
            }

        return image;


    }
    public static Vector3D[] makeRay(int width, int height, Camera camera) {
        double xPixel =  ((float)width/(camera.getWidth() - 1)) * ((float)camera.getRight() - camera.getLeft()) + camera.getLeft();
       // System.out.println(xPixel);
        double yPixel = ((float)height/(camera.getHeight() - 1)) * ((float)camera.getBottom() - camera.getTop()) + camera.getTop();
       // System.out.println(yPixel);
        double[] eyeA = {camera.getEx(),camera.getEy(),camera.getEz()};
        double[] lookA =  {camera.getLx(),camera.getLy(),camera.getLz()};
        double[] upA =  {camera.getUx(),camera.getUy(),camera.getUz()};
        Vector3D eye = new Vector3D(eyeA);
        //System.out.println("eye: " + eye.toString());
        Vector3D look = new Vector3D(lookA);
       // System.out.println("look: " + look.toString());
        Vector3D up = new Vector3D(upA);
        //System.out.println("up: " + up.toString());
        Vector3D eyeLookRay = eye.subtract(look);
        eyeLookRay = eyeLookRay.normalize();
        //System.out.println("eyelookray : " + eyeLookRay.toString());
        Vector3D goWidth = up.crossProduct(eyeLookRay);

        goWidth = goWidth.normalize();
       // System.out.println("gowidth : " + goWidth.toString());
        Vector3D goHeight = eyeLookRay.crossProduct(goWidth);
       // System.out.println("goheight: " + goHeight.toString());
        Vector3D point = eye.add(eyeLookRay.scalarMultiply(camera.getNear()));
        point = point.add(goWidth.scalarMultiply(xPixel));
        point = point.add(goHeight.scalarMultiply(yPixel));
       // System.out.println("point : " + point.toString());
        Vector3D rayVector = point.subtract(eye);
        rayVector = rayVector.normalize();
        //System.out.println("rayvector : " + rayVector.toString());

        Vector3D [] ray  = new Vector3D[] {point,rayVector};
        return ray;

    }
    public static double [] colorRay(Vector3D[] ray, Sphere sphere,double[] ambience, ArrayList<Light> lights){
        boolean intersects = false;

        double radius = sphere.getRadius();
        double [] centerA = new double[] {sphere.getSx(),sphere.getSy(), sphere.getSz()};
        Vector3D center = new Vector3D(centerA);
        Vector3D point = ray[0];
        Vector3D vector = ray[1];
        Vector3D baseToCenter = center.subtract(point);
        double projOnRay= baseToCenter.dotProduct(vector);
        //System.out.println("proj on ray: " + projOnRay);
        double disc = (radius*radius) - (baseToCenter.dotProduct(baseToCenter) - (projOnRay*projOnRay));
        //System.out.println("disc : " + disc);

        //System.out.println("disc: " + disc);
        if(disc >= 0){
            intersects = true;
            disc = Math.sqrt(disc);
            Vector3D intersection = point.add(vector.scalarMultiply(projOnRay - disc));
            //System.out.println( " intersection: "  + intersection.toString());
            Vector3D surfaceNormal = intersection.subtract(center);
            surfaceNormal = surfaceNormal.normalize();


            double[] sphereAmA = new double[] {sphere.getAmbientRed(),sphere.getAmbientGreen(),sphere.getAmbientBlue()};
            double[] sphereDifA = new double[]{sphere.getDiffuseRed(),sphere.getDiffuseGreen(),sphere.getDiffuseBlue()};
            double [] colorA =  ebeMultiply(sphereAmA,ambience);
            Vector3D color = new Vector3D(colorA);

            for(int i = 0 ; i < lights.size(); i++) {
                double[] emit = new double[]{lights.get(i).getRed(),lights.get(i).getGreen(),lights.get(i).getBlue()};
                double[] sourceA = new  double[]{lights.get(i).getLx(),lights.get(i).getLy(),lights.get(i).getLz()};
                Vector3D source = new Vector3D(sourceA);
                Vector3D lightRay = source.subtract(intersection);
                lightRay = lightRay.normalize();
                if(surfaceNormal.dotProduct(lightRay)>0){
                    double[] sphereLight = ebeMultiply(sphereDifA,emit);
                    Vector3D lightStrength = new Vector3D(sphereLight).scalarMultiply(surfaceNormal.dotProduct(lightRay));
                    color = color.add(lightStrength);
                    //System.out.println("color" + color.toString());

                }
            }
            return color.toArray();

        }
        return new double[]{0,0,0};

    }
    public static int[] roundToInt(double[] values) {
        int [] colors = new int[3];
        for(int i = 0; i< values.length; i++) {
            colors[i] = (int)Math.round(values[i] * 255);
            if(colors[i] > 255){colors[i] = 255;}
        }
            return colors;
    }
    public static void outputFile(int[][][] image, String arg) {
        File outfile = new File(arg.substring(0,arg.length()-4) + ".ppm");

        try {
            FileWriter stream = new FileWriter(outfile);
            stream.write( "P3\n");
            stream.write(image.length + " " + image[1].length + " 255\n");
            for(int i = 0; i < image.length ; i++) {
                for(int j = 0; j< image[i].length;j++){
                    for(int k = 0; k<3;k++){
                        stream.write(image[i][j][k] + " ");
                    }
                }
                stream.write("\n");
            }

            stream.close();


        }catch (IOException f){
            System.exit(3);
        }
    }
    public static int findCloseSphere(Vector3D [] ray, ArrayList<Sphere> spheres, Camera camera){
        ArrayList<Vector3D> intersections = new ArrayList<Vector3D>();
        ArrayList<Integer> intersectionSpheres = new ArrayList<Integer>();
        for(int i = 0; i <spheres.size();i++){
            boolean intersects = false;

            double radius = spheres.get(i).getRadius();
            double [] centerA = new double[] {spheres.get(i).getSx(),spheres.get(i).getSy(), spheres.get(i).getSz()};
            Vector3D center = new Vector3D(centerA);
            Vector3D point = ray[0];
            Vector3D vector = ray[1];
            Vector3D baseToCenter = center.subtract(point);
            double projOnRay= baseToCenter.dotProduct(vector);
            //System.out.println("proj on ray: " + projOnRay);
            double disc = (radius*radius) - (baseToCenter.dotProduct(baseToCenter) - (projOnRay*projOnRay));
            //System.out.println("disc : " + disc);

            //System.out.println("disc: " + disc);
            if(disc >= 0) {
                intersects = true;
                disc = Math.sqrt(disc);
                Vector3D intersection = point.add(vector.scalarMultiply(projOnRay - disc));
                intersections.add(intersection);
                intersectionSpheres.add(i);
            }
        }
        //System.out.println(intersections.toString());
        int shortest = 0;
        if(intersections.size() == 0){
            return 0;
        }
        else if(intersections.size()==1){
            return intersectionSpheres.get(0);
        }else{
            for(int i = 0; i < intersections.size(); i++) {
                if(intersections.get(i).distance(ray[0]) <= intersections.get(shortest).distance(ray[0])){
                    shortest = i;
                }
            }
        }


        return shortest;
    }

}
