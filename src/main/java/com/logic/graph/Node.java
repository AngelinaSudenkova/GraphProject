package com.logic.graph;

import java.util.Random;

public class Node {
    public int numberOfN;
    public double weight;
    public double min;
    public double max;


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

    @Override
    public String toString() {
        return numberOfN +"[weight=" + weight + ']';
    }
}
