package com.company;

public class Materials {
    private String name;
    private double ambientRed,ambientGreen, ambientBlue;
    private double diffuseRed,diffuseGreen, diffuseBlue;
    private double specRed,specGreen, specBlue;
    private double attenRed,attenGreen, attenBlue;
    double ns,ni,d,illum;

    public Materials() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmbientRed(double ambientRed) {
        this.ambientRed = ambientRed;
    }

    public void setAmbientGreen(double ambientGreen) {
        this.ambientGreen = ambientGreen;
    }

    public void setAmbientBlue(double ambientBlue) {
        this.ambientBlue = ambientBlue;
    }

    public void setDiffuseRed(double diffuseRed) {
        this.diffuseRed = diffuseRed;
    }

    public void setDiffuseGreen(double diffuseGreen) {
        this.diffuseGreen = diffuseGreen;
    }

    public void setDiffuseBlue(double diffuseBlue) {
        this.diffuseBlue = diffuseBlue;
    }

    public void setSpecRed(double specRed) {
        this.specRed = specRed;
    }

    public void setSpecGreen(double specGreen) {
        this.specGreen = specGreen;
    }

    public void setSpecBlue(double specBlue) {
        this.specBlue = specBlue;
    }

    public void setAttenRed(double attenRed) {
        this.attenRed = attenRed;
    }

    public void setAttenGreen(double attenGreen) {
        this.attenGreen = attenGreen;
    }

    public void setAttenBlue(double attenBlue) {
        this.attenBlue = attenBlue;
    }

    public void setNs(double ns) {
        this.ns = ns;
    }

    public void setNi(double ni) {
        this.ni = ni;
    }

    public void setD(double d) {
        this.d = d;
    }

    public void setIllum(double illum) {
        this.illum = illum;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "name='" + name + '\'' +
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
                ", ns=" + ns +
                ", ni=" + ni +
                ", d=" + d +
                ", illum=" + illum +
                '}';
    }
}
