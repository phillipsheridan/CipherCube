
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class CipherCube {

    private String plaintext;
    private String ciphertext;
    private String columnKey;
    private String rowKey;
    private int start = 0;
    private Character[][] square1 = new Character[5][5];
    private Character[][] square2 = new Character[5][5];
    private Character[][] square3 = new Character[5][5];
    private Character[][] square4 = new Character[5][5];
    private Character[][] square5 = new Character[5][5];
    private Character[][] square6 = new Character[5][5];

    /**
     * @param text maybe be plaintext or ciphertext
     * @param spec specifies plaintext (0) or ciphertext(1),
     */
    public CipherCube(String text, Boolean spec) {
        Character[][] ref;
        

        if (spec) {
            this.ciphertext = text;
            this.decipher();
        } else {
            this.plaintext = text;
            this.cipher();
        }
        
        if (text.length() != 150) {
            text = this.pad(text);
            this.plaintext = text;
        }
        System.out.println(text.length());
        for (int i = 0; i < 6; i++) {

            switch (i) {

                case 0:
                    ref = square1;
                    start = 0;

                    break;
                case 1:
                    ref = square2;
                    start = 25;

                    break;
                case 2:
                    ref = square3;
                    start = 50;

                    break;
                case 3:
                    ref = square4;
                    start = 75;

                    break;
                case 4:
                    ref = square5;
                    start = 100;

                    break;
                case 5:
                    ref = square6;
                    start = 125;

                    break;
                default:
                    ref = square1;
                    System.exit(1);

            }
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    ref[j][k] = plaintext.charAt(start);
                    start++;
                }

            }
        }
        // test if squares are filled

        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 5; j++) {

                System.out.print(square1[i][j] + " ");
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 5; j++) {

                System.out.print(square2[i][j] + " ");
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 5; j++) {

                System.out.print(square3[i][j] + " ");
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 5; j++) {

                System.out.print(square4[i][j] + " ");
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 5; j++) {

                System.out.print(square5[i][j] + " ");
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 5; j++) {

                System.out.print(square6[i][j] + " ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/phillip/NetBeansProjects/phillip/build/classes/test.txt");
        Scanner input = new Scanner(file);
        if (file.exists()) {

            String s = input.nextLine();
            //for testing
            //System.out.print(s);
            CipherCube cube = new CipherCube(s, false);

            //testing
            // System.out.println(cube.plaintext);
        }

    }

    /**
     * Adjust input text length to 250 characters
     */
    private String pad(String text) {
        StringBuilder strBldr = new StringBuilder(text);
        if (text.length() < 150) {
            while (strBldr.length() < 150) {
                strBldr.append("X");
            }
        } else {
            strBldr.replace(150, text.length() - 1, "");
        }

        text = new String(strBldr);
        return text;
    }

    private void run(boolean spec) throws IOException {
        File file = new File("test.txt");
        Scanner input = new Scanner(file);
        if (file.exists()) {
            if (spec) {
                String s = input.next();
                System.out.print(s);
            }

        }
    }

    private void cipher() {
        this.ciphertext = this.plaintext;
        System.out.println("feature not yet supported");
    }

    private void decipher() {
        this.plaintext = this.ciphertext;
        System.out.println("feature not yet supported");
    }

    /**
     * Accessor and mutator methods plaintext setter method may not be required
     */
    public String getPlaintext() {
        return this.plaintext;
    }

    public String getCiphertext() {
        return this.ciphertext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

}

/*

 class CSCI2070FinalProject extends Application {
    
 @Override
 public void start(Stage primaryStage) {
        
 }
 public static void main(String[] args) {
 // TODO code application logic here
 }
    
 }

 */
