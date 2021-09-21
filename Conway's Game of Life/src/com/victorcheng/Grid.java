package com.victorcheng;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Grid {
    public ArrayList<Point> liveCells;
    private final int Height;
    private final int Length;

    public Grid(ArrayList<Point> liveCells, int height, int length) {
        //constructor: set instance variables
        this.liveCells = liveCells;
        Height = height;
        Length = length;
    }

    public Grid Update() {
        //create blank board and blank list of live cells, 2 larger than original to allow for overflow
        int[][] newBoard = new int[Height + 2][Length + 2];
        ArrayList<Point> newLiveCells = new ArrayList<>();

        //populate blank board with overlapping sums
        for (Point p : liveCells) {
            AddToCells(newBoard, p.y + 1, p.x + 1);
        }

        //comb through the whole board and check for cells that can be born (3) or cells that can stay alive (12 or 13)
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < Length; j++) {
                int cellNumber = newBoard[i + 1][j + 1];//adds 1 because of overflow
                if (cellNumber == 3 || cellNumber == 13 || cellNumber == 12) {
                    //add cells to new board
                    newLiveCells.add(new Point(j, i));
                }
            }
        }

        //create new Grid object for the output
        Grid out = new Grid(newLiveCells, Height, Length);
        return (out);
    }

    private void AddToCells(int[][] b, int row, int col) {
        //adds 1 to all surrounding cell at a location on a board. 10 indicates a live cell position
        b[row][col] += 10;
        b[row + 1][col + 1] += 1;
        b[row + 1][col] += 1;
        b[row + 1][col - 1] += 1;
        b[row - 1][col + 1] += 1;
        b[row - 1][col] += 1;
        b[row - 1][col - 1] += 1;
        b[row][col + 1] += 1;
        b[row][col - 1] += 1;
    }

    public void PrintBoard(JFrame frame) {
        //prints to console
        int[][] board = new int[Height][Length];
        for (Point p : liveCells) {
            board[p.y][p.x] = 1;
        }
        for (int i = 0; i < Height; i++) {
            for (int j = 0; j < Length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //prints to JFrame
        JPanel panel = new JPanel(new GridLayout(board.length, board[0].length));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                JLabel label = new JLabel();
                label.setPreferredSize(new Dimension(5, 5));
                if (board[i][j] == 1) {
                    label.setBackground(Color.BLACK);
                } else {
                    label.setBackground(Color.WHITE);
                }
                label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                label.setOpaque(true);
                panel.add(label);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }
}
