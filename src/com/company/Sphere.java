package com.company;

import java.util.ArrayList;

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

    public double getSx() {
        return sx;
    }

    public double getSy() {
        return sy;
    }

    public double getSz() {
        return sz;
    }

    public double getRadius() {
        return radius;
    }

    public double getAmbientRed() {
        return ambientRed;
    }

    public double getAmbientGreen() {
        return ambientGreen;
    }

    public double getAmbientBlue() {
        return ambientBlue;
    }

    public double getDiffuseRed() {
        return diffuseRed;
    }

    public double getDiffuseGreen() {
        return diffuseGreen;
    }

    public double getDiffuseBlue() {
        return diffuseBlue;
    }

    public double getSpecRed() {
        return specRed;
    }

    public double getSpecGreen() {
        return specGreen;
    }

    public double getSpecBlue() {
        return specBlue;
    }

    public double getAttenRed() {
        return attenRed;
    }

    public double getAttenGreen() {
        return attenGreen;
    }

    public double getAttenBlue() {
        return attenBlue;
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
