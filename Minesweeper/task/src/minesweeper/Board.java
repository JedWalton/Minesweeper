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
            if(i < howManyMinesToPlace) {
                boardTokens.add(new Mine());
                countOfMines++;
            } else {
                boardTokens.add(new SafeCell());
            }
        }

        Collections.shuffle(boardTokens);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = boardTokens.get(i+j);
            }
        }
    }


    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].StringRepresentation);
            }
            System.out.println();
        }
    }
}
