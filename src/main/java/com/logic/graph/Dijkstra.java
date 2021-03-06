package com.logic.graph;
import java.util.*;
public class Dijkstra {
    int source;
    Graph graph;
    double[] distance;
    int[] previous;

    public Dijkstra(Graph graph){
        this.graph = graph;
        this.distance = new double[graph.numberOfVertexes];
        this.previous = new int[graph.numberOfVertexes];
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
        Node startNode = new Node(source,0);
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
            if(path.get(i) == start)
                break;
            output[path.size()-1-i] = path.get(i);
        }
        output[0] = start;
        return output;
    }
    public void drawPath(int start, int end){
        if(start >= graph.numberOfVertexes || start <0 || end<0 || end >= graph.numberOfVertexes){
            System.out.println("Podano nieprawid??owy w??ze?? pocz??tkowy");
            return;
        }
        this.source = start;
        calculate(start);
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
