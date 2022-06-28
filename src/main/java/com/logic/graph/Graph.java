package com.logic.graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph implements Iterable<ArrayList<Node>> {

    public int numberOfVertexes;
    public int rows;
    public int columns;
    public double min;
    public double max;

   public ArrayList<ArrayList<Node>> adjList;

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
        this.rows = rows;
        this.columns = columns;
        this.min = min;
        this.max = max;
        adjList = new ArrayList<ArrayList<Node>>(numberOfVertexes);
        for(int i = 0; i < numberOfVertexes; i++){
            adjList.add(new ArrayList<Node>());
        }
        genEdges();


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

   public void genEdges(){
        /*for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++ ){

                    int nodeNum = row*columns + col;
                    if(col != 0){// gen left edge
                        System.out.print(nodeNum + " left ");
                    }
                    if(col != columns-1){// gen right edge
                        System.out.print(nodeNum + " right ");
                    }
                    if(row != 0){ //gen upper
                        System.out.print(nodeNum + "  upper ");
                    }
                    if(row != rows-1){ //gen lower
                        System.out.print(nodeNum + " lower ");
                    }
                }
            System.out.println();
        }
*/
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
    public int[] getNeighboursNums(int nodeNum){
        int[] nodesNums = new int[adjList.get(nodeNum).size()];
        for(int i = 0; i < adjList.get(nodeNum).size();i++){
            nodesNums[i] = adjList.get(nodeNum).get(i).numberOfN;
        }
        return nodesNums;
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

    @Override
    public Iterator<ArrayList<Node>> iterator() {
        return adjList.iterator();
    }
}
