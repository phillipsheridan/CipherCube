//package ciphercubegui;

import javafx.scene.paint.Paint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Zach
 */
public class CipherCubeGUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        System.out.println(
                "Test");
        // Top pane - input controls
        GridPane topGrid = new GridPane();

        topGrid.setAlignment(Pos.CENTER);

        topGrid.setPadding(
                new Insets(11.5, 12.5, 13.5, 14.5));
        topGrid.setHgap(
                5.5);
        topGrid.setVgap(
                5.5);
        TextField tfInput = new TextField();

        tfInput.setPrefWidth(
                1300);
        CheckBox cbPlaintext = new CheckBox("Plaintext");

        cbPlaintext.setSelected(
                true);
        CheckBox cbCiphertext = new CheckBox("Ciphertext");

        cbCiphertext.setSelected(
                !cbPlaintext.isSelected());
        topGrid.add(cbPlaintext,
                0, 0);
        topGrid.add(cbCiphertext,
                1, 0);
        topGrid.add(tfInput,
                2, 0);

        // Middle pane - display plain/ciphertext 
        FlowPane outputBox = new FlowPane();

        outputBox.setPadding(
                new Insets(11, 12, 13, 14));
        outputBox.setHgap(
                5);
        outputBox.setVgap(
                5);

        String plaintext = "To save you time, let me say that I found them pretty useless when it came time to actually write my answer. First, they recommend stunning your audience with a witty and colorful comment. But then they tell how to formulate a 150-character that is professional and very, very boring. So how do you find a good balance between informative and creative?";
        plaintext = plaintext.replaceAll(" ", "");
        CipherCube cube = new CipherCube(plaintext, false);
        

        System.out.println(cube.getPlaintext());
        tfInput.setText(cube.getPlaintext());

//////////Temp representation of cube///////////////////////////////////////////
        StringBuilder strBldr = new StringBuilder();
        for (int i = 0;
                i < 150; i++) {
            strBldr.append('x');
        }
        String str = new String(strBldr);
        char[] c = str.toCharArray();
        int cubeSize = 5;
        Character[][][] arr = new Character[6][cubeSize][cubeSize];

////////////////////////////////////////////////////////////////////////////////      
        GridPane[][] cubeFaces = new GridPane[6][cubeSize];
        for (int i = 0;
                i < 6; i++) {
            strBldr.setLength(0); // clear the stringbuilder
            for (int j = 0; j < cubeSize; j++) {
                for (int k = 0; k < cubeSize; k++) {
                    strBldr.append(arr[i][j][k]);
                }

            }
        }

        // Array of paint objects to hold face colors
        Paint[] faceColors = new Paint[6];
        faceColors[0] = Paint.valueOf(
                "5b9ad4"); // blue
        faceColors[1] = Paint.valueOf(
                "c45a11"); // orange
        faceColors[2] = Paint.valueOf(
                "fed866"); // olive/tan
        faceColors[3] = Paint.valueOf(
                "548135"); // green
        faceColors[4] = Paint.valueOf(
                "fe00fe"); // pink 
        faceColors[5] = Paint.valueOf(
                "fefe00"); // yellow

        //TextField tf0 = new TextField();
        //tf0.setBackground(new Background(new BackgroundFill(faceColors[0], CornerRadii.EMPTY, Insets.EMPTY)));
        HBox cells = new HBox();
        // Bottom pane - output controls
        GridPane bottomGrid = new GridPane();

        bottomGrid.setAlignment(Pos.CENTER);

        bottomGrid.setPadding(
                new Insets(11.5, 12.5, 13.5, 14.5));
        bottomGrid.setHgap(
                100);
        bottomGrid.setVgap(
                5.5);
        HBox hBox = new HBox(10);

        hBox.setPrefWidth(
                60);
        Button btShuffle = new Button("Shuffle");
        Button btSolve = new Button("Solve");

        btShuffle.setDisable(
                false);
        btSolve.setDisable(
                true);
        btShuffle.setMinWidth(hBox.getPrefWidth());
        btSolve.setMinWidth(hBox.getPrefWidth());
        hBox.getChildren()
                .addAll(btShuffle, btSolve);
        bottomGrid.add(hBox,
                0, 0);

        // Base pane controlling layout
        BorderPane bp = new BorderPane();

        bp.setTop(topGrid);

    //bp.setCenter(outputBox);   
        populateCells(cells, cube.getPlaintext(), cube.getColorArray());
        bp.setCenter(cells);

        bp.setBottom(bottomGrid);

        /**
         * Event handlers for checkboxes
         */
        cbPlaintext.setOnAction(
                (ActionEvent e) -> {
                    // Disable plaintext options
                    cbCiphertext.setSelected(!cbPlaintext.isSelected());
                    btShuffle.setDisable(false);
                    btSolve.setDisable(true);
                }
        );

        cbCiphertext.setOnAction(
                (ActionEvent e) -> {
                    // Disable ciphertext options
                    cbPlaintext.setSelected(!cbCiphertext.isSelected());
                    btSolve.setDisable(false);
                    btShuffle.setDisable(true);
                }
        );

        /**
         * Event handlers for buttons
         */
        btShuffle.setOnMouseReleased(
                (e) -> {

                    cube.setPlaintext(tfInput.getText());
                    cube.populateCube(cube.getPlaintext());

                    cube.printSquares();

                    Character[][][] cubeLayout = cube.getCube();
                    StringBuilder stringBldr = new StringBuilder();
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 5; j++) {
                            for (int k = 0; k < 5; k++) {
                                stringBldr.append(cubeLayout[i][j][k]);
                            }
                        }
                    }

                    String retVal = stringBldr.toString();
                    cube.cipher(cube.getKey());
                    
                    populateCells(cells, cube.getCiphertext(), cube.getColorArray());
                    

                    tfInput.setText(cube.getCiphertext());
                    //refreshCells(cells, cube.getCiphertext());
                    System.out.println("retVal: " + retVal);

                    System.out.println("Cipher text:" + cube.getCiphertext());
                    System.out.println("ciphertext length:" + cube.getCiphertext().length());

                }
        );

        btSolve.setOnMouseReleased(
                (e) -> {
                    cube.populateCube(cube.getCiphertext());

                    cube.decipher(cube.getKey());
                    
                    cube.decipher(cube.getKey());
                    cube.populateCube(tfInput.getText());
                    cube.updateColorArray();
                    populateCells(cells, cube.getPlaintext(), cube.getColorArray());
                    cube.printSquares();

                    //cube.decipher(cube.getKey());
                    tfInput.setText(cube.getPlaintext());
                    //refreshCells(cells, cube.getPlaintext());
                    System.out.println("Plaintext: " + cube.getPlaintext());
                }
        );

        Scene scene = new Scene(bp);

        primaryStage.setTitle(
                "Professor's Cube Cipher");
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);

    }

    private class CubeCell extends Label {

        String color;
        Character c;

        CubeCell(String color, Character c) {
            this.setText(Character.toString(c));
            this.setStyle("-fx-background-color: " + color + "; -fx-padding: 10px;");

        }
    }

    public void populateCells(HBox h, String s, String[] a) {
        h.getChildren().clear();
        for (int i = 0; i < 150; i++) {
            h.getChildren().add(new CubeCell(a[i], s.charAt(i)));
        }

    }

}
