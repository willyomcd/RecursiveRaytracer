package com.company;

public class Light {
    private double lx, ly, lz;
    private double w;
    private double red,green, blue;


    public Light(){
        lx = 0;
        ly = 0;
        lz = 0;
        red = 0;
        green = 0;
        blue = 0;

    }
    public Light (double lx, double ly, double lz, double w, double red, double green, double blue) {
        this.lx = lx;
        this.ly = ly;
        this.lz = lz;
        this.w = w;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        //intellij generated toString
        return "Light{" +
                "lx=" + lx +
                ", ly=" + ly +
                ", lz=" + lz +
                ", w=" + w +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
