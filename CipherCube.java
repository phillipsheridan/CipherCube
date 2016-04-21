

//notes

/*
- need upside down method (maybe, could just hard code it)
- need to check for special case when column/row is 0 or 4, the face touching 
the column or row will be turn accordingly, need pass by reference method to do this
- mapping:
Face 1:
move a row left: 1 -> 4, 4 -> 3, 3 -> 2, 2 -> 1
move a row right: 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 1
move a column up: 1 -> 5, upside down(5) -> 3, 3 -> 6, upsidedown(6) -> 1
move a column down: upsidedown(1) -> 6, 6 -> 3, upsidedown(3) -> 5, 5 -> 1
Face 2:
move a row left: 2 -> 1, 1 -> 4, 4 -> 3, 3 -> 2
move a row right: 2 -> 3, 3 -> 4, 4 -> 1, 1 -> 2
move a column up: 2 -> row(5), row(5) -> 4, 4 -> row(6), row(6) -> 2
move a column down: 2 -> row(6), row(6) -> 4, 4 -> row(5), row(5) -> 2
Face3:
move a row left: 3 -> 2, 2 -> 1, 1 -> 4, 4 -> 3
move a row right: 3 -> 4, 4 -> 1, 1 -> 2, 2 -> 3
move a column up: upsideDown(3) -> 5, 5 -> 1, 4 -> upsidedown(1) -> 6, 6 -> 3
move a column down: 3 -> 6, upsidedwn(6) -> 1, 1 -> 5, upsidedown(5) -> 3
Face4:
move a row left: 4 -> 3, 3 -> 2, 2 -> 1, 1 -> 4
move a row right: 4 -> 1, 1 -> 2, 2 -> 3, 3 -> 4
move a column up: 4 -> row(5), row(5) -> 2, 2 -> row(6), row(6) -> 4
move a column down: 4 -> row(6), row(6) -> 2, 2 -> row(5), row(5) -> 4
Face5:
move a row left: 5 -> col(4), col(4) -> 6, 6 -> col(2), col(2) -> 5
move a row right: 5 -> col(2), col(2) -> 6, 6 -> col(4), col(4) -> 5
move a column up: upsidedown(5) -> 3, 3 -> 6, upsidedown(6) -> 1, 1 -> 5
move a column down: 5 -> 1, upsidedown(1) -> 6, 6 -> 3, upsidedown(3) -> 5
Face6:
move a row left: 6 -> col(2), col(2) -> 5, 5 -> col(4), col(4) -> 6 
move a row right: 6 -> col(4), col(4) -> 5, 5 -> col(2), col(2) -> 6
move a column up: 6 -> 3, upsidedown(3) -> 5, 5 -> 1, upsidedown(1) -> 6
move a column down: upsidedown(6) -> 1, 1 -> 5, upsidedown(5) -> 3, 3 -> 6
*/
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class CipherCube {

    private StringBuilder plaintext = new StringBuilder("");
    private StringBuilder ciphertext = new StringBuilder("");
    private StringBuilder key = new StringBuilder("11301 12412 13113");
    /*
     for key : column or row (0 or 1), face number (1-6),  index (0-4), 
     left or right/up or down (0 or 1), number of moves: four moves will reset 
     to original position so use (1,2, or 3)
    
     ie: "11301 12412 13113" would mean:
     "move face 1's fourth row left once, then move face 2's fifth row right twice,
     then move face 3's second row right three times"
     */

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
            this.ciphertext.append(text);
            this.decipher();
        } else {
            this.plaintext.append(text);
            this.cipher(key);
        }

        if (text.length() != 150) {
            text = this.pad(text);
            this.plaintext.append(text);
        }
        //test length
        //System.out.println(text.length());
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
/*
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
         */
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
     * Adjust input text length to 150 characters
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

    private void cipher(StringBuilder key) {
        String[] arr = key.toString().split(" ");
        for (String e : arr) {
            if (e.charAt(0) == 0) {
                columnSwap(e);
            } else if (e.charAt(0) == 1) {
                rowSwap(e);
            }
        }

    }

    private void columnSwap(String subKey) {
        Character[] temp = new Character[5];
          // face can be 1-6
        char face = subKey.charAt(1);
        //index can be 0-4
        char index = subKey.charAt(2);
        //direction can be up or down (0 or 1)
        char direction = subKey.charAt(3);
        //numberOfMoves can be 1-3
        char numberOfMoves = subKey.charAt(4);

        switch (face) {
            case '1':
                temp = square1[index];
                System.arraycopy(square1, 0, temp, 0, 5);
                
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                break;
            default:
                System.exit(1);

        }
    }

    private void rowSwap(String subKey) {
        // face can be 1-6 
        char face = subKey.charAt(1);
        //index can be 0-4
        char index = subKey.charAt(2);
        //direction can be left or right (0 or 1)
        char direction = subKey.charAt(3);
        //numberOfMoves can be 1-3
        char numberOfMoves = subKey.charAt(4);

        switch (face) {
            case '1':
                for (int i = 0; i < numberOfMoves; i++) {
                    
                }
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                break;
            default:
                System.exit(1);
        }
    }

    private void decipher() {

        System.out.println("feature not yet supported");
    }
    private Character[][] rightRotate(Character[][] a) {
        
    }
    // upside down 
    private Character[][] flip(Character[][] a) {
        
    }
    // this might not be neccessary not sure yet
    private Character[][] leftRotate(Character[][] a) {
        
    }
    

    /**
     * Accessor and mutator methods plaintext setter method may not be required
     */
    public String getPlaintext() {
        return this.plaintext.toString();
    }

    public String getCiphertext() {
        return this.ciphertext.toString();
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = new StringBuilder(plaintext);
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
