package com.logic.graph;

import java.util.Random;

public class Node {
    int numberOfN;
    double weight;

    Random random = new Random();

    public Node(int numberOfN ) {
        this.numberOfN = numberOfN;
        this.weight = random.nextDouble();
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
