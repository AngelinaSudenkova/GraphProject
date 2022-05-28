package com.graph.gui;

public class IsInside {


    public static boolean isInside( double clickedx,double clickedy,double x, double y, double radius) {
        if ((clickedx >= x && clickedx <= x + radius) && (clickedy >= y && clickedy <= y + radius)) {
            //System.out.println("We checked it ");
            //System.out.println("Yout clicked x is " + clickedx + "Your clicked y is " + clickedy);
           // System.out.println("And we are looking at  " + x + " and " + y + "with radius  " + radius);
            return true;
        } else {
            //System.out.println("Prosze wybrac wezel");
            return false;
        }
    }





}
