package com.logic.graph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class GraphReader  {
    String fileName;

    String buffer;
    Graph graph ;
    public int node;
    public int count;
    public double weight;
    public String del;

    public GraphReader(String fileName) {
        this.fileName = fileName;
    }


    public Graph readGraph (){
        try {
            FileReader file = new FileReader(fileName);
            Scanner src = new Scanner(file);
            buffer = src.nextLine();
            del = "[ ]+";
            String[] tokens = buffer.split(del);
            this.graph = new Graph(parseInt(tokens[0]), parseInt(tokens[1]));


            while (src.hasNextLine()) {

                buffer = src.nextLine();
                del = "[ :]+";
                tokens = buffer.split(del);
                for (int i = 1; i < tokens.length; i++) {
                    if (!tokens[i].equals("\t") && !tokens[i].equals(" ") && !tokens[i].equals("\n") ) {
                            node = parseInt(tokens[i]);
                            i++;
                            weight = parseDouble(tokens[i]);
                        //System.out.println("IM A TOKEN : " + tokens[i]);
                        graph.addEdgeFromFile(count,node,weight);
                    }
                }
                count++;
            }
        } catch (IOException e){
            System.out.println("Cannot read this file");
        }
       return graph;
    }
}
