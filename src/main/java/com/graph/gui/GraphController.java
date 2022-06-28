package com.graph.gui;

import com.logic.graph.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static javafx.scene.paint.Color.*;

public class GraphController implements Initializable {
    Graph graph;
    FileChooser fileChooser = new FileChooser();
    private GraphicsContext gc;
    ArrayList<GraphCoordinates> coordinates = new ArrayList<>();
    public Division division;

    @FXML
    private TextField maxWeight;

    @FXML
    private TextField minWeight;

    @FXML
    private TextField sizeOfGraph;

    @FXML
    private Canvas mainCanvas = new Canvas(400,400);
    private int[] path;
    public boolean isPathDrawn = false;
    public boolean isGraphDrawn = false;

    @FXML
    void generate(ActionEvent event) {
        if(!isGreater(minWeight,maxWeight)){
            ErrorWindow errorWindow = new ErrorWindow("Please put the valid numbers,\n min should be less then max");
            return;
        }
        try{
        String del = "x";
        String[] tokens = sizeOfGraph.getText().split(del);
        if(isGraphDrawn){
            coordinates.clear();
            clearArea();
        }
        int rows = parseInt(tokens[0]);
        int columns = parseInt(tokens[1]);
        double max = parseDouble(maxWeight.getText());
        double min = parseDouble(minWeight.getText());
        graph = new Graph(rows, columns, min, max);
        graph.printGraph(graph);

        draw(); } catch (NumberFormatException e){
            ErrorWindow errorFormat = new ErrorWindow("Please put number of vertices and columns " +
                    "as in example \n \"10x10\" ");
        }
    }

    public boolean isGreater(TextField min, TextField max){
        return parseDouble(max.getText()) > parseDouble(min.getText());
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("D:\\"));
    }


    @FXML
    void checkIntegrity(ActionEvent event) {
        if (graph != null) {
            Bfs bfs = new Bfs(graph, 0);
            if (bfs.isStronglyConnected) {
                PopupMessage.newMessage("Graph is strongly connected.","WHITE");
            } else {
                PopupMessage.newMessage("Graph isn't strongly connected.","RED");

            }
        } else {
            //draw();
            PopupMessage.newMessage("Please generate or read a graph.","RED");
        }
    }


    public void draw(){
        Division.setDivisions(graph);
        isGraphDrawn = true;
        int numberOfEdges = (graph.columns-1)*(graph.rows-1);
        double moveToRight = (double)350/(graph.columns-1);
        double moveToGround = (double)280/(graph.rows-1);
        double radius = Math.sqrt(250*300/(2*Math.PI*graph.numberOfVertexes));
        int count;

        gc = mainCanvas.getGraphicsContext2D();
        gc.setFill((Color.rgb(139, 0, 255)));
        gc.clearRect(0,0, mainCanvas.getWidth(),mainCanvas.getHeight());

        for(int i = 0; i < this.graph.rows; i++){
            for(int j = 0; j < this.graph.columns; j++){
                count = i*graph.columns+j;
                GraphCoordinates coord = new GraphCoordinates(moveToRight*j,moveToGround*i, j , i, count);
                coordinates.add(coord);
                gc.fillOval(moveToRight*j, moveToGround*i, radius,radius);
            }
        }

     // to the right
        for(int i = 0; i < this.graph.rows; i++) {
            for (int j = 0; j < this.graph.columns-1; j++) {

                gc.fillRect(moveToRight*j+radius*0.5, moveToGround*i+radius*0.5, moveToRight, radius*0.25);
            }
        }
        //To the ground
        for(int i = 0; i < this.graph.rows-1; i++) {
            for (int j = 0; j < this.graph.columns; j++) {
                gc.fillRect(moveToRight*j+radius*0.4, moveToGround*i+radius*0.5, radius*0.25, moveToGround);
            }
        }
    }


    public void drawFile(){
        Division.setDivisions(graph);
        isGraphDrawn = true;
        int numberOfEdges = (graph.columns-1)*(graph.rows-1);
        double moveToRight = (double)350/(graph.columns-1);
        double moveToGround = (double)280/(graph.rows-1);
        double radius = Math.sqrt(250*300/(2*Math.PI*graph.numberOfVertexes));
        int count;

        gc = mainCanvas.getGraphicsContext2D();
        gc.setFill((Color.rgb(139, 0, 255)));
        gc.clearRect(0,0, mainCanvas.getWidth(),mainCanvas.getHeight());

        for(int i = 0; i < this.graph.rows; i++){
            for(int j = 0; j < this.graph.columns; j++){
                count = i*graph.columns+j;
                GraphCoordinates coord = new GraphCoordinates(moveToRight*j,moveToGround*i, j , i, count);
                coordinates.add(coord);
                ArrayList<Node> nodes  = graph.adjList.get(count);

                    for(Node node : nodes){
                        if(node.numberOfN == count + 1){
                            gc.fillOval(moveToRight*j, moveToGround*i, radius,radius);
                            gc.fillOval(moveToRight*(j+1), moveToGround*i, radius,radius);
                            gc.fillRect(moveToRight*j+radius*0.5, moveToGround*i+radius*0.5, moveToRight, radius*0.25);

                         } else if(node.numberOfN == count + graph.columns) {
                            gc.fillOval(moveToRight*(j), moveToGround*i, radius,radius);
                            gc.fillOval(moveToRight*j, moveToGround*(i+1), radius,radius);
                            gc.fillRect(moveToRight*j+radius*0.4, moveToGround*i+radius*0.5, radius*0.25, moveToGround);}
                }

            }
        }

    }


    @FXML
    void clearArea() {
        graph = null;

        if(clickedNodes.size()!=0){
            clickedNodes.clear();
        }
        if(gc == null || isGraphDrawn == false){
            PopupMessage.newMessage("Please generate or read a graph.","RED");
            return;
        }
        isGraphDrawn = false;
        gc.clearRect(0,0, mainCanvas.getWidth(),mainCanvas.getHeight());
    }
    ArrayList<GraphCoordinates> clickedNodes = new ArrayList<>(2);
    @FXML
    void mouseClicked(MouseEvent event) {
        if(graph == null)
            return;
       double newx = event.getX();
       double newy = event.getY();
       //перенести в отдельные переменные и инициализировать их
        int numberOfEdges = (graph.columns-1)*(graph.rows-1);
        double moveToRight = (double)350/(graph.columns-1);
        double moveToGround = (double)280/(graph.rows-1);
        double radius = Math.sqrt(250*300/(2*Math.PI*graph.numberOfVertexes));
        //Color PURPLE = Color.web("8b00ff ");
      // System.out.println("X: " + newx + "Y: " + newy);
      // System.out.println("Number of clicked vertixes" + numberOfClicked);
        if(isPathDrawn == true){

        }
        if (clickedNodes.size() == 0) {
            for (GraphCoordinates node : coordinates) {
                if (IsInside.isInside(newx, newy, node.x, node.y, radius)) {

                    gc.setFill(WHITE);
                    clickedNodes.add(node);
                    gc.fillOval(node.x, node.y, radius, radius);
                    break;
                }
            }
        }
        else if (clickedNodes.size() == 1) {
            for (GraphCoordinates node : coordinates) {
                if (IsInside.isInside(newx, newy, node.x, node.y, radius)) {
                    if(clickedNodes.contains(node)){
                        gc.setFill(rgb(139, 0, 255));
                        gc.fillOval(node.x, node.y, radius, radius);
                        clickedNodes.remove(node);
                        break;
                    }
                    gc.setFill(WHITE);
                    clickedNodes.add(node);
                    gc.fillOval(node.x, node.y, radius, radius);
                    Dijkstra dijkstra = new Dijkstra(graph);
                    int start = clickedNodes.get(0).numberOfVert;
                    int end = clickedNodes.get(1).numberOfVert;
                    dijkstra.calculate(start);
                    this.path = dijkstra.reconstructPath(start,end);
                    drawPath(path,"white",radius,moveToGround,moveToRight);
                    isPathDrawn = true;
                    String message= "Cost of this path is :"+ dijkstra.getCost(end);
                    PopupMessage.newMessage(message,"WHITE");
                    break;
                }
            }
        }
        else if (clickedNodes.size() == 2) {
            for (GraphCoordinates node : coordinates) {
                if (IsInside.isInside(newx, newy, node.x, node.y, radius)) {
                    if(clickedNodes.contains(node)){
                        drawPath(path,"purple",radius,moveToGround,moveToRight);
                        clickedNodes.remove(node);
                        gc.setFill(WHITE);
                        gc.fillOval(clickedNodes.get(0).x,clickedNodes.get(0).y, radius, radius);
                        return;
                    }
                    drawPath(path,"purple",radius,moveToGround,moveToRight);
                    isPathDrawn = false;
                    clickedNodes.clear();
                    clickedNodes.add(node);
                    gc.setFill(WHITE);
                    gc.fillOval(node.x, node.y, radius, radius);
                    break;
                }
            }
        }
    }

    public void saveFile(ActionEvent event) {
        FileChooser file = new FileChooser();
        if(graph == null || !isGraphDrawn){
            PopupMessage.newMessage("Please create or read a graph.","RED");
            return;
        }
        File selectedFile = file.showSaveDialog(null);
            System.out.println(selectedFile.getAbsolutePath());
            GraphSave.save(graph,selectedFile.getAbsolutePath());
        }


    public void openFile(ActionEvent event) {
        try {
            FileChooser file = new FileChooser();
            File selectedFile = file.showOpenDialog(null);
            if(selectedFile != null){
                GraphReader readGraph = new GraphReader(selectedFile.getAbsolutePath());
                graph = readGraph.readGraph();
                drawFile();
        }


        } catch (Exception e){
            ErrorWindow fileError = new ErrorWindow("I can not read this file,\n please, choose another one");
        }

    }
    public void drawPath(int[] path, String color,double radius,double height,double width){
        int nodeNum,col,row,diff;
        for(int i = 0; i< path.length; i++ ){
            nodeNum = path[i];
            col = nodeNum % graph.columns;
            row = nodeNum / graph.columns;
            double currentWeight = graph.adjList.get(i).get(0).weight;
            if(color.equals("white")) {
                System.out.println("Current weight: " + currentWeight);
                if (currentWeight >= Division.getP1() && currentWeight < Division.getP2()) {
                    gc.setFill(RED);
                } else if (currentWeight >= Division.getP2() && currentWeight < Division.getP3()) {
                    gc.setFill(BLUE);
                } else if (currentWeight >= Division.getP3() && currentWeight < Division.getP4()) {
                    gc.setFill(GREEN);
                } else if (currentWeight >= Division.getP4() && currentWeight < Division.getP5()) {
                    gc.setFill(YELLOW);
                } else {
                    gc.setFill(ORANGE);
                }
            }
            else if (color.equals("purple")) {
                gc.setFill(Color.rgb(139, 0, 255));
            }
            gc.fillOval(col *width , row * height, radius, radius);
            if(i == path.length-1)
                break;
            diff = path[i] - path[i+1];
            if(diff == graph.columns){
                gc.fillRect(width*col+radius*0.4, height*(row-1)+radius*0.5, radius*0.25, height);
            }
            if(diff == -graph.columns){
                gc.fillRect(width*col+radius*0.4, height*row+radius*0.5, radius*0.25, height);
            }
            if(diff == 1){
                gc.fillRect(width*(col-1)+radius*0.5, height*row+radius*0.5, width, radius*0.25);
            }
            if(diff == -1){
                gc.fillRect(width*col+radius*0.5, height*row+radius*0.5, width, radius*0.25);
            }
        }
    }
    public void drawGraph() {

    }
}



