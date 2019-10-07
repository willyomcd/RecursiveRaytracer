package com.company;

public class Sphere {
    private double sx, sy, sz;
    private double radius;
    private double ambientRed,ambientGreen, ambientBlue;
    private double diffuseRed,diffuseGreen, diffuseBlue;
    private double specRed,specGreen, specBlue;
    private double attenRed,attenGreen, attenBlue;


    public Sphere(){

    }
    public Sphere (double sx, double sy, double sz, double radius, double ared, double agreen, double ablue,
                       double dred, double dgreen, double dblue,double sred, double sgreen, double sblue,
                       double atred, double atgreen, double atblue) {
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
        this.radius = radius;
        this.ambientRed = ared;
        this.ambientGreen = agreen;
        this.ambientBlue = ablue;
        this.diffuseRed = dred;
        this.diffuseGreen = dgreen;
        this.diffuseBlue = dblue;
        this.specRed = sred;
        this.specGreen = sgreen;
        this.specBlue = sblue;
        this.attenRed = atred;
        this.attenGreen = atgreen;
        this.attenBlue = atblue;
    }


    @Override
    public String toString() {
        //intellij generated toString
        return "Sphere{" +
                "sx=" + sx +
                ", sy=" + sy +
                ", sz=" + sz +
                ", radius=" + radius +
                ", ambientRed=" + ambientRed +
                ", ambientGreen=" + ambientGreen +
                ", ambientBlue=" + ambientBlue +
                ", diffuseRed=" + diffuseRed +
                ", diffuseGreen=" + diffuseGreen +
                ", diffuseBlue=" + diffuseBlue +
                ", specRed=" + specRed +
                ", specGreen=" + specGreen +
                ", specBlue=" + specBlue +
                ", attenRed=" + attenRed +
                ", attenGreen=" + attenGreen +
                ", attenBlue=" + attenBlue +
                '}';
    }
}
