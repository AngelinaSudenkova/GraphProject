package com.logic.graph;

public class Main {

    public static void main( String [] args){

        Graph graph = new Graph(5,5, 3, 10 );
        Bfs bfs = new Bfs(graph,0);
        System.out.println("  " + bfs.isStronglyConnected + "   ");
        Dijkstra dijkstra = new Dijkstra(graph,0);
        dijkstra.drawPath(0,12);
    }
}
