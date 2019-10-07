package com.company;

public class Camera{
    private double ex, ey, ez;
    private double lx,ly, lz;
    private double ux,uy, uz;
    private double near;
    private double left, right, bottom, top;
    private int width,height;

    public Camera(){
    }
    public Camera (double ex, double ey, double ez, double lx, double ly, double lz,
                   double ux, double uy, double uz,double near,double left, double right, double bottom,
                   double top, int width, int height) {
        this.ex = ex;
        this.ey = ey;
        this.ez = ez;
        this.lx = lx;
        this.ly = ly;
        this.lz = lz;
        this.ux = ux;
        this.uy = uy;
        this.uz = uz;
        this.near = near;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        //intellij generated toString
        return "Camera{" +
                "ex=" + ex +
                ", ey=" + ey +
                ", ez=" + ez +
                ", lx=" + lx +
                ", ly=" + ly +
                ", lz=" + lz +
                ", ux=" + ux +
                ", uy=" + uy +
                ", uz=" + uz +
                ", near=" + near +
                ", left=" + left +
                ", right=" + right +
                ", bottom=" + bottom +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

}
