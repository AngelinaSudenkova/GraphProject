package com.logic.graph;

import java.util.ArrayList;
import java.util.Random;

public class Node {
    int numberOfN;
    double weight;
    private ArrayList<Double> edges = new ArrayList<>(4);
    private ArrayList<Integer> neighboursNum = new ArrayList<>(4);
    Random random = new Random();

    public Node(int numberOfN ) {
        this.numberOfN = numberOfN;
        this.weight = random.nextDouble();
    }

    public Node(int numberOfN, double min,double max ) {
        this.numberOfN = numberOfN;
        this.weight = min + (max - min) * random.nextDouble();
    }

    public Node(int numberOfN, double weight) {
        this.numberOfN = numberOfN;
        this.weight = weight;
    }
    public void setNeighbour(int destination, double weight){
        edges.add(weight);
        neighboursNum.add(destination);
    }
    public ArrayList<Double> getEdges(){
        return edges;
    }
    public ArrayList<Integer> getNeighboursNum(){
        return neighboursNum;
    }
    @Override
    public String toString() {
        return numberOfN +"[weight=" + weight + ']';
    }
}
