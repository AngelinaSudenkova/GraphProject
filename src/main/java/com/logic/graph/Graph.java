package com.logic.graph;

import java.util.ArrayList;

public class Graph {

    int numberOfVertexes;

    ArrayList<ArrayList<Node>> adjList;


    public Graph(int numberOfVertexes) {
        this.numberOfVertexes = numberOfVertexes;
        adjList = new ArrayList<ArrayList<Node>>(numberOfVertexes);
        for(int i = 0; i < numberOfVertexes; i++){
            adjList.add(new ArrayList<Node>());
        }
    }



    void addEdge(int source, int dest){
         Node node1 = new Node(dest);
         adjList.get(source).add(node1);
        adjList.get(dest).add(new Node(source, node1.weight));
    }

    public void printGraph(Graph graph)
    {
        for (int i = 0; i < graph.numberOfVertexes; i++) {
            System.out.println("\nAdjacency list of vertex " + i);
            System.out.print("head");
            for (int j = 0; j < graph.adjList.get(i).size(); j++) {
                System.out.print(" -> "
                        + graph.adjList.get(i).get(j));
            }
            System.out.println();
        }
    }

}
