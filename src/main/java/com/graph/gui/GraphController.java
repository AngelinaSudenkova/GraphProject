package com.graph.gui;

import com.logic.graph.Bfs;
import com.logic.graph.Dijkstra;
import com.logic.graph.Graph;
import com.logic.graph.GraphReader;
import com.logic.graph.GraphSave;
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
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static javafx.scene.paint.Color.*;

public class GraphController implements Initializable {
    Graph graph;
    FileChooser fileChooser = new FileChooser();
    private GraphicsContext gc;
    ArrayList<GraphCoordinates> coordinates = new ArrayList<>();

    @FXML
    void addFile(ActionEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        graph = new GraphReader(file.getAbsolutePath()).readGraph();
        graph.printGraph(graph);
    }

    @FXML
    private TextField maxWeight;

    @FXML
    private TextField minWeight;

    @FXML
    private TextField sizeOfGraph;

    @FXML
    private Canvas mainCanvas = new Canvas(350,350);


    @FXML
    void generate(ActionEvent event) {
        String del = "x";
        String[] tokens = sizeOfGraph.getText().split(del);
        int rows = parseInt(tokens[0]);
        int columns = parseInt(tokens[1]);
        double max = parseDouble(maxWeight.getText());
        double min = parseDouble(minWeight.getText());
        graph = new Graph(rows, columns, min, max);
        graph.printGraph(graph);
        draw();
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

        int numberOfEdges = (graph.columns-1)*(graph.rows-1);
        double moveToRight = (double)300/graph.columns-1;
        double moveToGround = (double)300/graph.rows-1;
        double radius = 0.8*Math.sqrt(250*300/(2*Math.PI*graph.numberOfVertexes));
        int count;

        gc = mainCanvas.getGraphicsContext2D();
        gc.setFill(Color.valueOf("#8b00ff"));
        gc.clearRect(0,0, mainCanvas.getWidth(),mainCanvas.getHeight());




        System.out.println("Move to Right :" + moveToRight);
        System.out.println("Move to Ground :" + moveToGround);
        System.out.println("Radius: " + radius);

        for(int i = 0; i < this.graph.rows; i++){
            for(int j = 0; j < this.graph.columns; j++){
                count = i*graph.columns+j;
                GraphCoordinates coord = new GraphCoordinates(moveToRight*j,moveToGround*i, j , i, count);
                coordinates.add(coord);
                gc.fillOval(moveToRight*j, moveToGround*i, radius,radius);
            }
        }
//        for (GraphCoordinates g : coordinates){
//            System.out.println(g);
//        }

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


    @FXML
    void clearArea(ActionEvent event) {
        graph = null;
        if(gc == null){
            PopupMessage.newMessage("Please generate or read a graph.","WHITE");
            return;
        }
        gc.clearRect(0,0, mainCanvas.getWidth(),mainCanvas.getHeight());
    }
    int numberOfClicked = 0;
    int vertix1 = 0;
    int vertix2 = 0;
    @FXML
    void mouseClicked(MouseEvent event) {
        if(graph == null)
            return;
       double newx = event.getX();
       double newy = event.getY();
       //перенести в отдельные переменные и инициализировать их
        int numberOfEdges = (graph.columns-1)*(graph.rows-1);
        double moveToRight = (double)300/graph.columns-1;
        double moveToGround = (double)300/graph.rows-1;
        double radius = 0.8*Math.sqrt(250*300/(2*Math.PI*graph.numberOfVertexes));

      // System.out.println("X: " + newx + "Y: " + newy);
      // System.out.println("Number of clicked vertixes" + numberOfClicked);
    if (numberOfClicked == 0) {
        for (GraphCoordinates g : coordinates) {
            if (IsInside.isInside(newx, newy, g.x, g.y, radius)) {
                numberOfClicked++;
                gc.setFill(WHITE);

                vertix1 = g.numberOfVert;
                gc.fillOval(g.x, g.y, radius, radius);
                System.out.println("Vertex1 is " + vertix1);
                return;
            } }  } else if (numberOfClicked == 1) {
                for (GraphCoordinates gr : coordinates) {
                    if (IsInside.isInside(newx, newy, gr.x, gr.y, radius)) {
                        numberOfClicked++;
                        gc.setFill(WHITE);
                        System.out.println("You clicked a vertex number: " + gr.numberOfVert);
                        vertix2 = gr.numberOfVert;
                        gc.fillOval(gr.x, gr.y, radius, radius);
                        System.out.println("Vertex2 is " + vertix2);

                        Dijkstra dijkstra = new Dijkstra(graph);
                        dijkstra.drawPath(vertix1, vertix2);
                        return;
                    }}
                    } else if (numberOfClicked == 2) {
                        numberOfClicked = 0;
                        PopupMessage.newMessage("Choose another two nodes.","WHITE");
                    }

                }

    public void saveFile(ActionEvent event) {
        FileChooser file = new FileChooser();
        File selectedFile = file.showSaveDialog(null);
        if(selectedFile != null){
            if(graph == null){
                PopupMessage.newMessage("Please create or read a graph.","RED");
                return;
            }
            System.out.println(selectedFile.getAbsolutePath());
            GraphSave.save(graph,selectedFile.getAbsolutePath());
        }
    }

    public void openFile(ActionEvent event) {
        FileChooser file = new FileChooser();
        File selectedFile = file.showOpenDialog(null);
        if(selectedFile != null){
            System.out.println(selectedFile.getAbsolutePath());

        }



    }

    public void drawGraph() {

    }
}



