package com.graph.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.RED;

public class PopupMessage {
   static public void newMessage(String message,String color){
       Stage dialogStage = new Stage();
       dialogStage.setMinHeight(250);
       dialogStage.setMinWidth(250);
       dialogStage.initModality(Modality.WINDOW_MODAL);

       Text text = new Text(message);
       text.setStyle("-fx-font: 18 arial");
       text.setFill(Paint.valueOf(color));
       VBox vbox = new VBox(text);
       vbox.setBackground(new Background(new BackgroundFill(Color.valueOf("8b00ff"), CornerRadii.EMPTY, Insets.EMPTY)));
       vbox.setAlignment(Pos.CENTER);
       vbox.setPadding(new Insets(15));

       dialogStage.setScene(new Scene(vbox));
       dialogStage.show();
   }
}
