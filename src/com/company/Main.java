package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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
                        System.out.println(look);
                        double lx =scanner.nextDouble();
                        double ly =scanner.nextDouble();
                        double lz =scanner.nextDouble() ;
                        String up = scanner.next();
                        System.out.println(up);
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
                        break;
                    case "ambient":
                        double r = scanner.nextDouble();
                        double g = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        break;
                    case "light"
                }


            }
            scanner.close();
            //System.out.println(driverArray.size());
            //System.out.println(modelArray.size());
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
