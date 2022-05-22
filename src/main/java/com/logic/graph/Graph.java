package com.logic.graph;

import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

    public int numberOfVertexes;
    public int rows;
    public int columns;
    public double min;
    public double max;

    ArrayList<ArrayList<Node>> adjList;

    public Graph(int numberOfVertexes) {
        this.numberOfVertexes = numberOfVertexes;
        adjList = new ArrayList<ArrayList<Node>>(numberOfVertexes);
        for(int i = 0; i < numberOfVertexes; i++){
            adjList.add(new ArrayList<Node>());
        }
    }

    public Graph() {
    }

    public Graph(int rows, int columns) {
        this.numberOfVertexes = rows*columns;
        adjList = new ArrayList<ArrayList<Node>>(numberOfVertexes);
        for(int i = 0; i < numberOfVertexes; i++){
            adjList.add(new ArrayList<Node>());
        }
        this.rows = rows;
        this.columns = columns;
    }
    public Graph( int rows, int columns, double min, double max) {
        this.numberOfVertexes = rows*columns;
        adjList = new ArrayList<ArrayList<Node>>(numberOfVertexes);
        for(int i = 0; i < numberOfVertexes; i++){
            adjList.add(new ArrayList<Node>());
        }
        this.rows = rows;
        this.columns = columns;
        this.min = min;
        this.max = max;
    }
    void addEdge(int source, int dest){
        if(max == 0 && min == 0){
            Node node1 = new Node(dest);
            adjList.get(source).add(node1);
            adjList.get(dest).add(new Node(source, node1.weight));
        }else {
            Node node1 = new Node(dest, min, max);
            adjList.get(source).add(node1);
            adjList.get(dest).add(new Node(source, node1.weight));
        }
    }

    void addEdgeFromFile(int source, int dest, double weight){
            Node node1 = new Node(dest, weight);
            adjList.get(source).add(node1);
    }

    void genEdges(){
        int lastNode = columns - 1;
        for ( int i = 0; i < numberOfVertexes - columns ; i++){
            if ( i == lastNode){
                lastNode += columns;
                addEdge(i, i+columns);
            } else {
                addEdge(i, i+1);
                addEdge(i,i+columns);
            }
        }
        for ( int i = numberOfVertexes - columns; i < numberOfVertexes - 1 ; i++){
            addEdge(i,i+1);
        }
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

    public int getNumberOfVertexes() {
        return numberOfVertexes;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void setNumberOfVertexes(int numberOfVertexes) {
        this.numberOfVertexes = numberOfVertexes;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
