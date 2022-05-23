package com.logic.graph;

import java.util.Comparator;

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if(o1.weight > o2.weight){
            return 1;
        } else if (o1.weight < o2.weight) {
            return -1;
        }
        return 0;

    }
}
