package com.logic.graph;

import java.util.ArrayList;

public class Main {

    public static void main( String [] args){

        //Graph graph = new Graph(3,3, 3, 10 );

        GraphReader read = new GraphReader("C:\\Users\\PC\\Downloads\\mygraph2.txt");
        Graph graph = read.readGraph();
        Bfs bfs = new Bfs(graph,0);
        if(bfs.isStronglyConnected){
            System.out.println("Graf jest spójny");
        }
        else {
            System.out.println("Graf nie jest spójny");
        }
        //Dijkstra dijkstra = new Dijkstra(graph);
        //dijkstra.drawPath(2,8);

    }
}
