package com.graph.gui;
import com.logic.graph.Graph;

public class Division {
    private  static double p1;
    private  static double p2;
    private  static double p3;
    private static double p4;
    private static double p5;
    private static double p6;
    private static double step;

    public static void setDivisions(Graph graph){
        step = (Math.abs(graph.max)+ Math.abs(graph.min))/6;
        p1 = graph.min;
        p2 = graph.min + step;
        p3 = graph.min + 2*step;
        p4 = graph.min + 3*step;
        p5 = graph.min + 4*step;
        p6 = graph.min + 4*step;
        System.out.println("Step : " + step + " \np1 " + p1 + " p 2 " + p2 + "p3" + p3 + " p4 " +p4 + " p5" + p5);


    }

    public static double getP1() {
        return p1;
    }

    public static double getP2() {
        return p2;
    }

    public static double getP3() {
        return p3;
    }

    public static double getP4() {
        return p4;
    }

    public static double getP5() {
        return p5;
    }
}
