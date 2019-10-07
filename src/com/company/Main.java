package com.company;

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
        Camera camera;

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
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
