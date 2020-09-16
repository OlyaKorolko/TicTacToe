package com.TicTac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    private final int ROWS = 3;
    private final int COLS = 3;
    private final byte PLAYER1 = 0;
    private final byte PLAYER2 = 1;
    char[][] ticTacField;
    private byte flag = PLAYER1;
    private int fillCounter = 0;
    private List<Integer> marksOfPlayer1 = new ArrayList<>();
    private List<Integer> marksOfPlayer2 = new ArrayList<>();
    private Scanner scan;

    TicTacToe() {
        ticTacField = new char[ROWS][COLS];
        scan = new Scanner(System.in);
    }

    public void getIntoGame() {
        fillMatrix(ticTacField);
        System.out.println("You are a Player1! Type a number position");
        while (fillCounter < 9) {
            if (flag == PLAYER1) {
                System.out.println("Player1:");
            } else {
                System.out.println("Player2:");
            }
            if (!makeAMove()) {
                continue;
            }
            printMatrix(ticTacField);
            if (getResult()) {
                break;
            }
            flag = changeFlag(flag);
        }
        if (fillCounter == 9) {
            System.out.println("Its a tie!");
        }
    }

    private boolean getResult() {
        if (checkIfSequence(marksOfPlayer1) || checkIfSequence(marksOfPlayer2)) {
            if (flag == PLAYER1) {
                System.out.println("You won!");
            } else {
                System.out.println("You lost!");
            }
            return true;
        }
        return false;
    }
    private void fillMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                matrix[i][j] = '.';
            }
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private byte changeFlag(byte flag) {
        if (flag == PLAYER1) {
            flag = PLAYER2;
        } else {
            flag = PLAYER1;
        }
        return flag;
    }

    private boolean makeAMove() {
        byte curMove = scan.nextByte();
        curMove--;

        if (curMove < 0 || curMove > 8) {
            System.out.println("Position out of bounds.");
            return false;
        } else if (ticTacField[curMove / ROWS][curMove % COLS] != '.') {
            System.out.println("Already taken!");
            return false;
        }

        if (flag == PLAYER1) {
            ticTacField[curMove / ROWS][curMove % COLS] = 'X';
            marksOfPlayer1.add(curMove + 1);
        } else {
            ticTacField[curMove / ROWS][curMove % COLS] = 'O';
            marksOfPlayer2.add(curMove + 1);
        }
        fillCounter++;
        return true;
    }

    private boolean checkIfSequence(List<Integer> marksOfPlayer) {
        return containsCheck(marksOfPlayer, 1, 2, 3) || containsCheck(marksOfPlayer, 1, 4, 7) ||
                containsCheck(marksOfPlayer, 1, 5, 9) || containsCheck(marksOfPlayer, 2, 5, 8) ||
                containsCheck(marksOfPlayer, 3, 5, 7) || containsCheck(marksOfPlayer, 3, 6, 9) ||
                containsCheck(marksOfPlayer, 4, 5, 6) || containsCheck(marksOfPlayer, 7, 8, 9);
    }

    private static boolean containsCheck(List<Integer> marksOfPlayer, int a, int b, int c) {
        return marksOfPlayer.contains(a) && marksOfPlayer.contains(b) && marksOfPlayer.contains(c);
    }
}