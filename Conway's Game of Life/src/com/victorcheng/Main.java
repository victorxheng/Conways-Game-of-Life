package com.victorcheng;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    private static final int numberOfRounds = 20;

    public static void main(String[] args) {
        //create new board manually
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        //convert the manual board into a grid list by finding the locations of all the live points
        ArrayList<Point> listOfCells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    listOfCells.add(new Point(j, i));
                }
            }
        }
        //create grid object
        Grid b = new Grid(listOfCells, board.length, board[0].length);

        //create graphics
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setVisible(true);

        //print first board
        b.PrintBoard(frame);

        //start looping through rounds
        for (int i = 0; i < numberOfRounds; i++) {
            try {
                Thread.sleep(1000);//sleep function
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            b = b.Update();
            b.PrintBoard(frame);
        }
    }
}