package minesweeper;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    Tile[][] board;

    public Board(int boardSize, int howManyMinesToPlace) {
        this.board = new Tile[boardSize][boardSize];

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
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].StringRepresentation);
            }
            System.out.println();
        }
    }

    public void calculateHowManyMinesThereAreAroundEachEmptyCellAndUpdateBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                int minesSurrounding = checkMineAround(i, j);
                if(minesSurrounding != -1 && minesSurrounding != 0){
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
}
