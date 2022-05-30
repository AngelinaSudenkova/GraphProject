package com.logic.graph;

import java.io.FileWriter;

public class GraphSave {
    static public void save(Graph graph, String path) {
        graph.printGraph(graph);
        try {
            FileWriter output = new FileWriter (path);
            output.write( graph.columns + " " + graph.rows+"\n");
            for (int nodeNum = 0; nodeNum < graph.getNumberOfVertexes(); nodeNum++) {
                output.write("\t");
                System.out.print("\t");
                for (Node edge : graph.adjList.get(nodeNum)) {
                    output.write(" "+ edge.numberOfN +":" + edge.weight);
                    System.out.println(" "+edge.numberOfN+":"+edge.weight);
                }

                if (nodeNum == graph.numberOfVertexes - 1)
                    break;
                output.write("\n");
            }
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}