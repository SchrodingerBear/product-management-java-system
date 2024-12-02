package main;

import java.util.Arrays;
import java.util.Random;

public class TerrainGenerator {
    public static void main(String[] args) {
        int rows = 50;
        int columns = 50;
        
        // flatworld(rows, columns);
        world(rows, columns);
    }
    
    public static void flatworld(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("12 ");
            }
            System.out.println();
        }
    }
    
    public static void world(int rows, int columns) {
        int borderWidth = 8; // Thickness of the border

        int[][] grid = new int[rows][columns]; // Create a 2D grid to store the values

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i < borderWidth || i >= rows - borderWidth || j < borderWidth || j >= columns - borderWidth) {
                    int min = 16; // Minimum border value
                    int max = 17; // Maximum border value

                    Random random = new Random();
                    int randomNumber = random.nextInt(max - min + 1) + min;

                    grid[i][j] = randomNumber;
                } else {
                    int min = 12;  // Minimum value
                    int max = 13; // Maximum value

                    Random random = new Random();
                    int randomNumber = random.nextInt(max - min + 1) + min;

                    grid[i][j] = randomNumber;
                }
            }
        }

        // border bottom
        for (int j = borderWidth; j < columns - borderWidth; j++) {
            grid[rows - borderWidth - 1][j] = 19;
        }

        // border top
        for (int j = borderWidth; j < columns - borderWidth; j++) {
            grid[borderWidth][j] = 24;
        }

        // border right
        for (int i = borderWidth; i < rows - borderWidth; i++) {
            grid[i][columns - borderWidth - 1] = 21;
        }

        // border left
        for (int i = borderWidth; i < rows - borderWidth; i++) {
            grid[i][borderWidth] = 22;
        }

        // Modify the first element of borderright to 26
        int[] borderleft = new int[rows - 2 * borderWidth];
        int[] borderright = new int[rows - 2 * borderWidth];
        int[] bordertop = new int[columns - 2 * borderWidth];
        int[] borderbottom = new int[columns - 2 * borderWidth];

        for (int i = borderWidth; i < rows - borderWidth; i++) {
            borderleft[i - borderWidth] = grid[i][borderWidth];
            borderright[i - borderWidth] = grid[i][columns - borderWidth - 1];
        }

        for (int j = borderWidth; j < columns - borderWidth; j++) {
            bordertop[j - borderWidth] = grid[borderWidth][j];
            borderbottom[j - borderWidth] = grid[rows - borderWidth - 1][j];
        }

        borderright[0] = 27; 
        borderleft[0] = 26; 
        borderleft[borderleft.length - 1] = 28; 
        borderright[borderright.length - 1] = 29; 

        // Set the updated border values back to the grid
        for (int i = borderWidth; i < rows - borderWidth; i++) {
            grid[i][columns - borderWidth - 1] = borderright[i - borderWidth];
            grid[i][borderWidth] = borderleft[i - borderWidth];
        }

        // Print the updated grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        // Print the border values
        // System.out.println("bordertop: " + Arrays.toString(bordertop));
        // System.out.println("borderbottom: " + Arrays.toString(borderbottom));
        // System.out.println("borderleft: " + Arrays.toString(borderleft));
        // System.out.println("borderright: " + Arrays.toString(borderright));
    }
}