package csci2070finalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CSCI2070FinalProject extends Application {
    
   @Override
   public void start(Stage primaryStage) {

       FlowPane pane = new FlowPane();
       pane.setPadding(new Insets(11, 12, 13, 14));
       pane.setHgap(5);
       pane.setVgap(5);

       FlowPane[] faces = new FlowPane[6];
       for(FlowPane fp : faces)
           for(int i = 0; i < 5; i++)
               fp.getChildren().add(new FlowPane());

       /** colors:
           face 1 - blue
           face 2 - orange
           face 3 - olive
           face 4 - green
           face 5 - pink
           face 6 - yellow
        */
        
        



       // pane.getChildren().addAll;

       Scene scene = new Scene(pane);
       primaryStage.setTitle("Professor's Cube Cipher");
       primaryStage.setScene(scene);
       primaryStage.show();
   }

   public static void main(String[] args) {

   }
    
}
