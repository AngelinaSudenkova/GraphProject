package com.logic.graph;

import java.util.Random;

public class Main {

    public static void main( String [] args){

        Graph graph = new Graph(5,3, 3, 10 );
        for(int i=0;i<graph.numberOfVertexes;i++){
            for (Node node: graph.adjList.get(i)) {
                System.out.print(node.numberOfN + ": " +  node.weight + " ");
            }
            System.out.println();
        }
        Bfs bfs = new Bfs(graph,0);
        System.out.println("  " + bfs.isStronglyConnected + "   ");
        Dijkstra dijkstra = new Dijkstra(graph,0);
        for (double dist:dijkstra.distance) {
            System.out.println(dist);
        }
    }
}
