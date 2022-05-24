package com.logic.graph;
import java.util.*;
class Dijkstra {
    int source;
    Graph graph;
    double[] distance;
    int[] previous;

    Dijkstra(Graph graph,int source){
        this.graph = graph;
        this.source = source;
        this.distance = new double[graph.numberOfVertexes];
        this.previous = new int[graph.numberOfVertexes];
        calculate(source);
    }
    public void calculate(int source){
        int[] previous = new int[graph.numberOfVertexes];
        for(int i = 0; i< graph.numberOfVertexes;i++ ){
            previous[i] = -1;
        }
        double[] distance;
        distance = new double[graph.numberOfVertexes];
        Arrays.fill(distance,
                Double.MAX_VALUE);
        distance[source] = 0;
        boolean[] visited = new boolean[graph.numberOfVertexes];
        for(int i = 0; i< graph.numberOfVertexes;i++ ){
            visited[i] = false;
        }
        PriorityQueue<Node> pq = new PriorityQueue(new NodeComparator());
        Node startNode = new Node(0,0);
        pq.add(startNode);
        while(!pq.isEmpty()){
            Node currentNode = pq.poll();
            int index = currentNode.numberOfN;
            double minValue = currentNode.weight;
            visited[index] = true;
            if (distance[index] < minValue)
                continue;
            for (Node edge:graph.adjList.get(index)){
                if(visited[edge.numberOfN])
                    continue;
                double newDistance = distance[index] + edge.weight;
                if (newDistance < distance[edge.numberOfN]){
                    previous[edge.numberOfN] = index;
                    distance[edge.numberOfN] = newDistance;
                    pq.add(edge);
                }

            }
        }
        this.distance = distance;
        this.previous = previous;
    }
    public int[] reconstructPath(int start, int end){
        ArrayList<Integer> path = new ArrayList<>(graph.numberOfVertexes);

        int at = end;
        while(at != -1){
            path.add(at);
            at = previous[at];
        }
        int[] output = new int[path.size()];
        for(int i = 0; i<path.size()-1;i++){
            output[path.size()-1-i] = path.get(i);
        }
        return output;
    }
    public void drawPath(int start, int end){
        int[] path = reconstructPath( start, end);
        System.out.println("Koszt drogi to "+ getCost(end)+" a jej przebieg od "+start+" do "+end + " to:");
        System.out.print("\t\t\t\t\t");
        for(int i = 0; i<path.length;i++){
            System.out.print(path[i]);
            if(i != path.length-1)
                System.out.print(" => ");
        }
        
    }
    public double getCost(int nodeNum){
        return distance[nodeNum];
    }
}
