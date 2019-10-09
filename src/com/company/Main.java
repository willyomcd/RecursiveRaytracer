package com.company;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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
                        camera = new Camera(ex,ey,ez,lx,ly,lz,ux,uy,ux,dValue,bLeft,bRight,bBottum,bTop,width,height);
                        System.out.println(camera.toString());
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
                        System.out.println(light.toString());
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
                        System.out.println(sphere.toString());
                        spheres.add(sphere);
                }


            }
            scanner.close();
            render(camera,lights,spheres, ambience);
        } catch (IOException e) {
            System.exit(1);
        }
    }
    public static void render(Camera camera, ArrayList<Light> lights,ArrayList<Sphere> spheres, double[] ambience){
        for(int sphere = 0; sphere <spheres.size(); sphere++){
            for(int i = 0; i <camera.getWidth(); i++) {
                for(int j = 0; j< camera.getHeight(); j++) {
                    Vector3D [] ray = new Vector3D[2];
                    ray = makeRay(i,j,camera);
                    int color = colorRay(ray, spheres.get(sphere));
                }
            }
        }

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
       // System.out.println("up: " + up.toString());
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
    public static int colorRay(Vector3D[] ray, Sphere sphere){
        boolean intersects = false;
        double radius = sphere.getRadius();
        double [] centerA = new double[] {sphere.getSx(),sphere.getSy(), sphere.getSz()};
        Vector3D center = new Vector3D(centerA);
        Vector3D point = ray[0];
        Vector3D vector = ray[1];
        Vector3D baseToCenter = center.subtract(point);
        double projOnRay= baseToCenter.dotProduct(vector);
        double disc = baseToCenter.dotProduct(baseToCenter) - (projOnRay*projOnRay);
        disc = Math.sqrt(disc);
        if(disc <= radius){
            intersects = true;
        }



        return 0;
    }
}
