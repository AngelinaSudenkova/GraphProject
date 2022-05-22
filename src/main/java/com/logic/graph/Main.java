package com.logic.graph;

import java.util.Random;

public class Main {

    public static void main( String [] args){

//        Graph graph = new Graph(2,3, -2, 0 );
//        graph.genEdges();
//        graph.printGraph(graph);

        Graph graph = new GraphReader("D:\\Studia\\SEM2\\JIMP2\\projektJAVA\\FinalProject\\src\\main\\java\\com\\logic\\graph\\graph").readGraph();
        System.out.println(graph.getColumns() + " " + graph.getRows());
        graph.printGraph(graph);
    }
}
