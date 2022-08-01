package minesweeper;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    Tile[][] board;
    int boardSize;
    int howManyMinesOnBoard;

    public Board(int boardSize, int howManyMinesToPlace) {
        this.board = new Tile[boardSize][boardSize];
        this.boardSize = boardSize;
        this.howManyMinesOnBoard = howManyMinesToPlace;

        ArrayList<Tile> boardTokens = new ArrayList<>();
        int totalBoardSize = boardSize * boardSize;

        int countOfMines = 0;
        for (int i = 0; i < totalBoardSize; i++) {
            if (countOfMines < howManyMinesToPlace) {
                boardTokens.add(new Mine());
                countOfMines++;
            } else {
                boardTokens.add(new SafeCell());
            }
        }

        Collections.shuffle(boardTokens);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = boardTokens.get((j * boardSize) + i);
            }
        }

        calculateHowManyMinesThereAreAroundEachEmptyCellAndUpdateBoard();
    }

    public void displayBoard() {
        System.out.println();
        System.out.println(" │123456789│\n—│—————————│");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < board[0].length; j++) {
                if (!this.board[i][j].StringRepresentation.equals("X")) {
                    System.out.print(this.board[i][j].StringRepresentation);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("|");
        }
        System.out.println("—│—————————│");
    }

    public void calculateHowManyMinesThereAreAroundEachEmptyCellAndUpdateBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                int minesSurrounding = checkMineAround(i, j);
                if (minesSurrounding != -1 && minesSurrounding != 0) {
                    this.board[i][j].StringRepresentation = Integer.toString(minesSurrounding);
                }
            }
        }
    }

    public int checkMineAround(int row, int col) {
        int result = 0;

        if (this.board[row][col].getClass() == Mine.class) {
            return -1;
        }

        int upShift = 1;
        int downShift = 1;
        int leftShift = 1;
        int rightShift = 1;

        if (row == 0) {
            upShift = 0;
        }
        if (row == this.board.length - 1) {
            downShift = 0;
        }
        if (col == 0) {
            leftShift = 0;
        }
        if (col == this.board.length - 1) {
            rightShift = 0;
        }
        for (int i = row - upShift; i <= row + downShift; i++) {
            for (int j = col - leftShift; j <= col + rightShift; j++) {
                if (this.board[i][j].getClass() == Mine.class) {
                    result++;
                }
            }
        }
        return result;
    }

    public boolean isGameOver() {
        /* if all cells are marked correctly */
        int minesCorrectlyMarked = 0;
        int minesIncorrectlyMarked = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (this.board[i][j].getClass() == MarkedMine.class) {
                    minesCorrectlyMarked++;
                } else if (this.board[i][j].getClass() == MarkedSafeCell.class) {
                    minesIncorrectlyMarked++;
                }
            }
        }
        if(minesCorrectlyMarked == this.howManyMinesOnBoard) {
            if(minesIncorrectlyMarked == 0) {
                return true;
            }
        }
        return false;
    }
}
