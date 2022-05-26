package com.graph.gui;

import com.logic.graph.Graph;
import com.logic.graph.GraphReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class GraphController implements Initializable {

    FileChooser fileChooser = new FileChooser();

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


//    @FXML
//    void addFile(MouseEvent event) { File file = fileChooser.showOpenDialog(new Stage());    }
    @FXML
    void addFile(ActionEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        Graph graph = new GraphReader(file.getAbsolutePath()).readGraph();
        graph.printGraph(graph);
    }

    @FXML
    private TextField maxWeight;

    @FXML
    private TextField minWeight;

    @FXML
    private TextField sizeOfGraph;


    @FXML
    void generate(ActionEvent event) throws IOException {
        String del = "x";
        String []tokens = sizeOfGraph.getText().split(del);
        int rows = parseInt(tokens[0]);
        int columns = parseInt(tokens[1]);
        double max = parseDouble(maxWeight.getText());
        double min = parseDouble(minWeight.getText());
        Graph graph = new Graph(rows, columns,min,max);
        graph.printGraph(graph);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("D:\\"));
    }
}