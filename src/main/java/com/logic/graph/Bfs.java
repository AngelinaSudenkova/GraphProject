package com.logic.graph;

import java.util.PriorityQueue;

class Bfs {
    int source;
    Graph graph;
    boolean isStronglyConnected;
    public Bfs(Graph graph,int source){
        this.source = source;
        this.graph = graph;
        this.isStronglyConnected = checkIntegrity(graph);
    }
    public boolean checkIntegrity(Graph graph){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.getNumberOfVertexes()];
        visited[source] = true;
        queue.add(source);
        while(!queue.isEmpty()){
            int nodeNum = queue.poll();
            int[] neighbours = graph.getNeighboursNums(nodeNum);
            for (int next: neighbours) {
                if (!visited[next]){
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        for (boolean x:visited) {
            if(!x)
                return false;
        }
        return true;
    }
}
