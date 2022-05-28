package com.graph.gui;

public class GraphCoordinates {
   double x;
   double y;
    int i;
    int j;
    int numberOfVert;



    public GraphCoordinates(double x, double y, int j, int i, int number) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
        this.numberOfVert = number;
    }

    @Override
    public String toString(){
    return "X:" + x + " Y: " + y +"j :" + j + "i "+ i + "number of vert : " + numberOfVert;

    }
}
