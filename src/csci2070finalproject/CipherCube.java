/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci2070finalproject;

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
    // Allows us to dynamically resize cube to n*n
    private int cubeSize;
    
    private int start = 0;
    
    private Character[][][] cube = new Character[6][cubeSize][cubeSize];

    /**
     * @param text maybe be plaintext or ciphertext
     * @param spec specifies plaintext (0) or ciphertext(1),
     * @param cubeSize
     */
    public CipherCube(String text, Boolean spec, int cubeSize) {
        Character[][] ref;
        this.cubeSize = cubeSize;
        
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
                    ref = cube[0];
                    start = 0;

                    break;
                case 1:
                    ref = cube[1];
                    start = 25;

                    break;
                case 2:
                    ref = cube[2];
                    start = 50;

                    break;
                case 3:
                    ref = cube[3];
                    start = 75;

                    break;
                case 4:
                    ref = cube[4];
                    start = 100;

                    break;
                case 5:
                    ref = cube[5];
                    start = 125;

                    break;
                default:
                    // ref assignment seems unnecessary - omit in revision                    
                    ref = cube[0];
                    System.exit(1);

            }
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    ref[j][k] = plaintext.charAt(start);
                    start++;
                }
            }
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

    // Yet to be implemented
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
                temp = cube[0][index];
                System.arraycopy(cube[0], 0, temp, 0, 5);
                
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

    // Yet to be implemented
    private void decipher() {

        System.out.println("feature not yet supported");
    }
    
    // Rotate faces, taking neighboring faces into account
    private void rotate(int face, boolean direction) {
        int tot = this.cubeSize - 1;
        
        Character[][] transformation = new Character[this.cubeSize][this.cubeSize];
        
        for (int i = 0; i < tot; i++) 
            for (int j = 0; i < tot; j++) 
                // Rotate right if direction is true
                if(direction) 
                    transformation[j][tot-i] = this.cube[face][i][j];         
                // Rotate left if direction is false
                else 
                    transformation[tot-j][i] = this.cube[face][i][j];
         
        
        updateNeighborFaces(transformation, face, direction);
        
        // might need to copy transformation into current face due to referencing issues / pass by reference vs pass by value        
        this.cube[face] = transformation;
        
    }
    
    private void updateNeighborFaces(Character[][] transformation, int face, boolean direction) {
        
        // 3*4 array to hold faces to update
        // swapNeighbors[0] affects faces 1 & 3
        // swapNeighbors[1] affects faces 2 & 4
        // swapNeighbors[2] affects faces 5 & 6        
        int[][] swapNeighbors = new int[][] {{1,3,4,5}, {0,2,4,5}, {0,1,2,3}};
        
        if(direction) {
            switch(face) {
                case 0: case 2: 
                    
                    break;
                case 1: case 3:
                    break;
                case 4: case 5: 
                    break;
                    
                default:
                    System.out.println("Out of bounds face: error");
                    break;
            }
        }
        else {
            
        }  
    } 
    
    /**
     * Accessor and mutator methods plaintext, setter method may not be required
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