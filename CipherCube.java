//package ciphercubegui;

/** imports used in testing functionality
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
*/

public class CipherCube {

    private StringBuilder plaintext = new StringBuilder("");
    private StringBuilder ciphertext = new StringBuilder("");
    private StringBuilder key = new StringBuilder("02303 01002 02101 00103 11111 12111 10111");
    /*
     for key : column or row (0 or 1), face number (0-2),  index (0-4), 
     left or right/up or down (0 or 1), number of moves: four moves will reset 
     to original position so use (1,2, or 3)
    
     ie: "11301 12412 13113" would mean:
     "move face 1's fourth row left once, then move face 2's fifth row right twice,
     then move face 3's second row right three times"
     */

    private int start = 0;
    private int cubeSize;
    private Character[][] square1 = new Character[5][5];
    private Character[][] square2 = new Character[5][5];
    private Character[][] square3 = new Character[5][5];
    private Character[][] square4 = new Character[5][5];
    private Character[][] square5 = new Character[5][5];
    private Character[][] square6 = new Character[5][5];
    private Character[][][] cube = {square1, square2, square3, square4, square5, square6};

    /**
     * @param text maybe be plaintext or ciphertext
     * @param spec specifies plaintext (0) or ciphertext(1),
     */
    /**
     * public static void main(String[] args) throws IOException { File file =
     * new
     * File("/Users/phillip/NetBeansProjects/phillip/build/classes/test.txt");
     * Scanner input = new Scanner(file); if (file.exists()) {
     *
     * String s = input.nextLine();
     *
     * CipherCube cube = new CipherCube(s, false); System.out.println("Before
     * cipher:\n"); cube.printSquares(); cube.cipher(cube.getKey());
     * System.out.println("\nAfter cipher:\n"); cube.printSquares();
     * cube.decipher(cube.getKey()); System.out.println("\nAfter decipher:\n");
     * cube.printSquares();
     *
     * }
     *
     * }
     */
    public CipherCube(String text, Boolean spec) {
        Character[][] ref;

        if (text.length() != 150) {
            text = this.pad(text);
        }
        if (spec) {
            this.ciphertext.append(text);

        } else {
            this.plaintext.append(text);
        }
        
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
	//this.cipher(key.toString());
        //System.out.println(this.plaintext);
        //this.cipher(this.getPlaintext());
        //this.decipher(this.getCiphertext());

    }

    public void printSquares() {
        
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    System.out.print(cube[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
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

    public void cipher(String key) {
        String[] arr = key.split(" ");
        for (String e : arr) {
            if (e.charAt(0) == '0') {
                System.out.println("in cipher function: calling columnSwapE");
                columnSwapE(e);
                
                
                
            } else if (e.charAt(0) == '1') {
                System.out.println("in cipher function: calling rowSwapE");
                rowSwapE(e);
                
                
                
            }
        }
////////////////////////////////////////////////////////////////////////////////        
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    str.append(cube[i][j][k]);
                }
            }
        }
        this.setCiphertext(str.toString()); 
        this.printSquares();
////////////////////////////////////////////////////////////////////////////////
    }

    public void decipher(String key) {

        String[] arr = key.split(" ");
        //reverse array
        String[] temp = new String[arr.length];
        int k = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            temp[k] = arr[i];
            k++;
        }
        for (String e : temp) {
            if (e.charAt(0) == '0') {
                columnSwapD(e);
            } else if (e.charAt(0) == '1') {
                rowSwapD(e);
            }
        }
////////////////////////////////////////////////////////////////////////////////
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                for(int s = 0; s < 5; s++) {
                    str.append(cube[i][j][s]);
                }
            }
        }
        this.setPlaintext(new String(str));
        
////////////////////////////////////////////////////////////////////////////////
    }

    private void columnSwapE(String subKey) {
        Character[] temp = new Character[5];
        // face can be 0-2
        char face = subKey.charAt(1);
        //index can be 0-4
        int index = Integer.parseInt(Character.toString(subKey.charAt(2)));
        //direction can be up or down (0 or 1)
        char direction = subKey.charAt(3);
        //numberOfMoves can be 1-3
        char numberOfMoves = subKey.charAt(4);

        switch (face) {
            case '0':
                //j is for quick reversing arrays
                if (direction == '0') {
                    // direction is up
                    int j = 4;
                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        //reinitialize j
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get 1 column
                            temp[i] = cube[0][i][index];
                        }
                        // row(6) -> 1
                        for (int i = 0; i < 5; i++) {
                            cube[0][i][index] = cube[5][index][i];
                        }
                        // upsidedown(3) -> row(6)
                        for (int i = 0; i < 5; i++) {
                            cube[5][index][i] = cube[2][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // row(5) -> 3
                        for (int i = 0; i < 5; i++) {
                            cube[2][i][index] = cube[4][index][i];
                        }
                        // upsidedown(1) -> row(5)
                        for (int i = 0; i < 5; i++) {
                            cube[4][index][i] = temp[j];
                            j--;
                        }

                    }
                } else {
                    // direction is down
                    int j = 4;
                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            // get 1 column
                            temp[i] = cube[0][i][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(row(5)) -> 1
                            cube[0][i][index] = cube[5][index][j];
                            j--;
                        }
                        //reset j
                        j = 4;
                        for (int i = 0; i < 5; i++) {
                            //3 -> row(5)
                            cube[4][index][i] = cube[2][i][index];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(row(6)) -> 3
                            cube[2][i][index] = cube[5][index][j];
                            j--;
                        }
                        //reset j
                        j = 4;
                        for (int i = 0; i < 5; i++) {
                            // 1 -> row(6)
                            cube[5][index][i] = temp[i];
                        }
                    }
                }
                break;
            case '1':
                //j is for quick reversing arrays
                int j = 4;
                //main loop
                //up
                if (direction == '0') {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square2 column
                            temp[i] = cube[1][i][index];
                        }
                        // 6 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = cube[5][i][index];
                        }
                        // upsidedown(4) -> 6
                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = cube[3][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // upsidedown(5) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = cube[4][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // 2 -> 5
                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = temp[i];
                        }
                    }
                } else //down
                {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square2 column
                            temp[i] = cube[1][i][index];
                        }

                        // 5 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = cube[4][i][index];

                        }
                        // upsidedown(4) -> 5

                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = cube[3][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // upsidedown(6) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = cube[5][j][index];
                            j--;

                        }
                        //reset j
                        j = 4;
                        // 2 -> 6
                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = temp[i];
                        }

                    }

                }

                break;
            case '2':
                //j is for quick reversing arrays
                j = 4;
                //main loop
                //up
                if (direction == '0') {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square5 column
                            temp[i] = cube[4][i][index];
                        }
                        // 2 -> 5
                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = cube[1][i][index];
                        }
                        // 6 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = cube[5][i][index];
                        }
                        // upsidedown(4) -> 6
                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = cube[3][i][index];
                        }
                        //reset j
                        j = 4;
                        // upsidedown(5) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = temp[i];
                        }
                    }
                } else //down
                {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square5 column
                            temp[i] = cube[4][i][index];
                        }
                        // upsidedown(4) -> 5
                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = cube[3][j][index];
                            j--;
                        }
                        j = 4;
                        // upsidedown(6) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = cube[5][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // 2 -> 6
                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = cube[1][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // 5 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = temp[i];
                        }
                    }
                }
                break;
            default:
                System.exit(1);

        }
    }

    private void columnSwapD(String subKey) {
        Character[] temp = new Character[5];
        // face can be 0-2
        char face = subKey.charAt(1);
        //index can be 0-4
        int index = Integer.parseInt(Character.toString(subKey.charAt(2)));
        //direction can be down or up (0 or 1)
        char direction = subKey.charAt(3);
        //numberOfMoves can be 1-3
        char numberOfMoves = subKey.charAt(4);

        //switch direction
        if (direction == '0') {
            direction = '1';
        } else {
            direction = '0';
        }

        switch (face) {
            case '0':
                //j is for quick reversing arrays
                if (direction == '0') {
                    // direction is still up
                    int j = 4;
                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get 1 column
                            temp[i] = cube[0][i][index];
                        }

                        // row(6) -> 1
                        for (int i = 0; i < 5; i++) {
                            cube[0][i][index] = cube[5][index][i];

                        }
                        // upsidedown(3) -> row(6)

                        for (int i = 0; i < 5; i++) {
                            cube[5][index][i] = cube[2][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // row(5) -> 3
                        for (int i = 0; i < 5; i++) {
                            cube[2][i][index] = cube[4][index][i];

                        }
                        // upsidedown(1) -> row(5)
                        for (int i = 0; i < 5; i++) {
                            cube[4][index][i] = temp[j];
                            j--;
                        }

                    }
                } else {
                    // direction is down
                    int j = 4;
                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            // get 1 column
                            temp[i] = cube[0][i][index];
                        }

                        for (int i = 0; i < 5; i++) {
                            // upsidedown(row(5)) -> 1
                            cube[0][i][index] = cube[4][index][j];
                            j--;

                        }
                        //reset j
                        j = 4;
                        for (int i = 0; i < 5; i++) {
                            //3 -> row(5)
                            cube[4][index][i] = cube[2][i][index];
                        }

                        for (int i = 0; i < 5; i++) {
                            // upsidedown(row(6)) -> 3
                            cube[2][i][index] = cube[5][index][j];
                            j--;

                        }
                        //reset j
                        j = 4;
                        for (int i = 0; i < 5; i++) {
                            // 1 -> row(6)
                            cube[5][index][i] = temp[i];
                        }

                    }
                }

                break;
            case '1':
                //j is for quick reversing arrays
                int j = 4;
                //main loop
                //up
                if (direction == '0') {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square2 column
                            temp[i] = cube[1][i][index];
                        }

                        // 6 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = cube[5][i][index];

                        }
                        // upsidedown(4) -> 6

                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = cube[3][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // upsidedown(5) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = cube[4][j][index];
                            j--;

                        }
                        //reset j
                        j = 4;
                        // 2 -> 5
                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = temp[i];
                        }

                    }
                } else //down
                {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square2 column
                            temp[i] = cube[1][i][index];
                        }

                        // 5 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = cube[4][i][index];

                        }
                        // upsidedown(4) -> 5

                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = cube[3][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // upsidedown(6) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = cube[5][j][index];
                            j--;

                        }
                        //reset j
                        j = 4;
                        // 2 -> 6
                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = temp[i];
                        }

                    }

                }

                break;

            case '2'://j is for quick reversing arrays
                j = 4;
                //main loop
                //up
                if (direction == '0') {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square5 column
                            temp[i] = cube[4][i][index];
                        }

                        // 2 -> 5
                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = cube[1][i][index];

                        }
                        // 6 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = cube[5][i][index];
                        }
                        // upsidedown(4) -> 6
                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = cube[3][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // upsidedown(5) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = temp[j];
                            j--;
                        }
                    }
                } else //down
                {
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get square5 column
                            temp[i] = cube[4][i][index];
                        }
                        // upsidedown(4) -> 5
                        for (int i = 0; i < 5; i++) {
                            cube[4][i][index] = cube[3][j][index];
                            j--;
                        }
                        j = 4;
                        // upsidedown(6) -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][i][index] = cube[5][j][index];
                            j--;
                        }
                        //reset j
                        j = 4;
                        // 2 -> 6
                        for (int i = 0; i < 5; i++) {
                            cube[5][i][index] = cube[1][i][index];
                        }
                        //reset j
                        // 5 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][i][index] = temp[i];
                        }
                    }
                }
                break;
            default:
                System.exit(1);
        }
    }

    private void rowSwapE(String subKey) {
        Character[] temp = new Character[5];
        // face can be 0-2 
        char face = subKey.charAt(1);
        //index can be 0-4
        int index = Integer.parseInt(Character.toString(subKey.charAt(2)));
        //direction can be left or right (0 or 1)
        char direction = subKey.charAt(3);
        //numberOfMoves can be 1-3
        char numberOfMoves = subKey.charAt(4);

        switch (face) {
            case '0': //left
                if (direction == '0') {
                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get a row
                            temp[i] = cube[0][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 2 -> 1
                            cube[0][index][i] = cube[1][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 2
                            cube[1][index][i] = cube[2][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 4 -> 3
                            cube[2][index][i] = cube[3][index][i];
                        }
                        //1 -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][index][i] = temp[i];
                        }
                    }
                } else {
                    //right
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get a row
                            temp[i] = cube[0][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 4 -> 1
                            cube[0][index][i] = cube[3][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 4
                            cube[3][index][i] = cube[2][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 2 -> 3
                            cube[2][index][i] = cube[1][index][i];
                        }
                        //1 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][index][i] = temp[i];
                        }
                    }
                }
                break;
            case '1':
                if (direction == '0') {
                    //left

                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get row 2
                            temp[i] = cube[1][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 2
                            cube[1][index][i] = cube[2][index][i];
                        }
                        for (int i = 0; i < 5; i++) {                            // 4 -> 3
                            cube[2][index][i] = cube[3][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 1 -> 4
                            cube[3][index][i] = cube[0][index][i];
                        }
                        //2 -> 1
                        for (int i = 0; i < 5; i++) {
                            cube[0][index][i] = temp[i];
                        }
                    }
                } else {
                    //right
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get row 2
                            temp[i] = cube[1][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 1 -> 2 
                            cube[1][index][i] = cube[0][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 4 -> 1
                            cube[0][index][i] = cube[3][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 4
                            cube[3][index][i] = cube[2][index][i];
                        }
                        //2 -> 3
                        for (int i = 0; i < 5; i++) {
                            cube[2][index][i] = temp[i];
                        }
                    }
                }
                break;
            case '2':
                int j = 4;
                if (direction == '0') {
                    //left

                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get row 5
                            temp[i] = cube[4][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // col(3) -> row(5)
                            cube[4][index][i] = cube[2][i][index];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(6) -> col(3)
                            cube[2][i][index] = cube[5][index][j];
                            j--;
                        }
                        //reset j
                        j = 4;
                        for (int i = 0; i < 5; i++) {
                            // col(1) -> 6
                            cube[5][index][i] = cube[0][i][index];
                        }
                        //upsidedown(5) -> col(1)
                        for (int i = 0; i < 5; i++) {
                            cube[0][i][index] = temp[j];
                            j--;
                        }
                    }
                } else {
                    //right
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get row 5
                            temp[i] = cube[4][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(col(1)) -> 5
                            cube[4][index][i] = cube[0][j][index];
                            j--;
                        }
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            // 6 -> col(1)
                            cube[0][i][index] = cube[5][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(col(3)) -> 6
                            cube[5][index][i] = cube[2][j][index];
                            j--;
                        }
                        j = 4;
                        //5 -> col(3)
                        for (int i = 0; i < 5; i++) {
                            cube[2][i][index] = temp[i];
                        }
                    }
                }
                break;
            default:
                System.exit(1);
        }
    }

    private void rowSwapD(String subKey) {
        Character[] temp = new Character[5];
        // face can be 0-2 
        char face = subKey.charAt(1);
        //index can be 0-4
        int index = Integer.parseInt(Character.toString(subKey.charAt(2)));
        //direction can be left or right (0 or 1)
        char direction = subKey.charAt(3);
        //numberOfMoves can be 1-3
        char numberOfMoves = subKey.charAt(4);

        //switch direction
        if (direction == '0') {
            direction = '1';
        } else {
            direction = '0';
        }

        switch (face) {
            case '0':

                if (direction == '0') {
                    //right
                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get a row
                            temp[i] = cube[0][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 2 -> 1
                            cube[0][index][i] = cube[1][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 2
                            cube[1][index][i] = cube[2][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 4 -> 3
                            cube[2][index][i] = cube[3][index][i];
                        }
                        //1 -> 4
                        for (int i = 0; i < 5; i++) {
                            cube[3][index][i] = temp[i];
                        }
                    }
                } else {
                    //left
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get a row
                            temp[i] = cube[0][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 4 -> 1
                            cube[0][index][i] = cube[3][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 4
                            cube[3][index][i] = cube[2][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 2 -> 3
                            cube[2][index][i] = cube[1][index][i];
                        }
                        //1 -> 2
                        for (int i = 0; i < 5; i++) {
                            cube[1][index][i] = temp[i];
                        }
                    }
                }
                break;
            case '1':
                if (direction == '0') {
                    //left

                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get row 2
                            temp[i] = cube[1][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 2
                            cube[1][index][i] = cube[2][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 4 -> 3
                            cube[2][index][i] = cube[3][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 1 -> 4
                            cube[3][index][i] = cube[0][index][i];
                        }
                        //2 -> 1
                        for (int i = 0; i < 5; i++) {
                            cube[0][index][i] = temp[i];
                        }
                    }
                } else {
                    //right
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {

                        for (int i = 0; i < 5; i++) {
                            //get row 2
                            temp[i] = cube[1][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 1 -> 2 
                            cube[1][index][i] = cube[0][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 4 -> 1
                            cube[0][index][i] = cube[3][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // 3 -> 4
                            cube[3][index][i] = cube[2][index][i];
                        }
                        //2 -> 3
                        for (int i = 0; i < 5; i++) {
                            cube[2][index][i] = temp[i];
                        }
                    }
                }
                break;
            case '2':
                int j = 4;
                if (direction == '0') {
                    //left

                    //main loop
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            //get row 5
                            temp[i] = cube[4][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // col(3) -> row(5)
                            cube[4][index][i] = cube[2][i][index];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(6) -> col(3)
                            cube[2][i][index] = cube[5][index][j];
                            j--;
                        }
                        //reset j
                        j = 4;
                        for (int i = 0; i < 5; i++) {
                            // col(1) -> 6
                            cube[5][index][i] = cube[0][i][index];
                        }
                        //upsidedown(5) -> col(1)
                        for (int i = 0; i < 5; i++) {
                            cube[0][i][index] = temp[j];
                            j--;
                        }
                    }
                } else {
                    //right
                    for (int m = 0; m < Integer.parseInt(Character.toString(numberOfMoves)); m++) {
                        for (int i = 0; i < 5; i++) {
                            //get row 5
                            temp[i] = cube[4][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(col(1)) -> 5
                            cube[4][index][i] = cube[0][j][index];
                            j--;
                        }
                        j = 4;

                        for (int i = 0; i < 5; i++) {
                            // 6 -> col(1)
                            cube[0][i][index] = cube[5][index][i];
                        }
                        for (int i = 0; i < 5; i++) {
                            // upsidedown(col(3)) -> 6
                            cube[5][index][i] = cube[2][j][index];
                            j--;
                        }
                        j = 4;
                        //5 -> col(3)
                        for (int i = 0; i < 5; i++) {
                            cube[2][i][index] = temp[i];
                        }
                    }
                }
                break;
            default:
                System.exit(1);
        }
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

    public String getKey() {
        return this.key.toString();
    }

    public void setPlaintext(String plaintext) {
        // update plaintext & call cipher function on new plaintext
        this.plaintext = new StringBuilder(plaintext);
        this.cipher(new String(this.key));
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = new StringBuilder(ciphertext);
        //this.decipher(new String(this.key));
    }
    
    public Character[][][] getCube() {
        return this.cube;
    }

}
