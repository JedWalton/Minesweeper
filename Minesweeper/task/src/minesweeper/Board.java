package minesweeper;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    Tile[][] board;
    int boardSize;
    int howManyMinesOnBoard;
    int numberOfFreeTurns;
    boolean gameOver;

    public Board(int boardSize, int howManyMinesToPlace) {
        this.board = new Tile[boardSize][boardSize];
        this.boardSize = boardSize;
        this.howManyMinesOnBoard = howManyMinesToPlace;
        this.numberOfFreeTurns = 0;
        this.gameOver = false;

        initTiles(howManyMinesToPlace);

    }

    public void initTiles(int howManyMinesToPlace) {
        this.howManyMinesOnBoard = howManyMinesToPlace;

        ArrayList<Tile> boardTokens = new ArrayList<>();
        int totalBoardSize = this.boardSize * this.boardSize;

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
    }

    public void displayBoard() {
        System.out.println();
        System.out.println(" │123456789│\n—│—————————│");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < board[0].length; j++) {
                if (this.gameOver) {
                    if (this.board[i][j].getClass() == MarkedMine.class || this.board[i][j].getClass() == Mine.class) {
                        System.out.print("X");
                    } else {
                        System.out.print(this.board[i][j].StringRepresentation);
                    }
                } else {
                    if (!this.board[i][j].StringRepresentation.equals("X")) {
                        System.out.print(this.board[i][j].StringRepresentation);
                    } else {
                        System.out.print(".");
                    }
                }
            }
            System.out.println("|");
        }
        System.out.println("—│—————————│");
    }

    public void exploreCell(int X, int Y) {
        int numberOfMinesAround = calculateNumberOfMinesSurroundingTile(X, Y);

        if (doesCellContainMine(X, Y)) {
            System.out.println("You stepped on a mine and failed!");
            this.gameOver = true;
        } else if (numberOfMinesAround > 0) {
            /* do not explore all surrounding tiles automatically */
            this.board[X][Y].StringRepresentation = Integer.toString(numberOfMinesAround);
        } else if (numberOfMinesAround == 0) {
            /* explore all surrounding tiles automatically */
            this.board[X][Y] = new ExploredSafeCell();
            //exploreSurroundingTiles(X, Y);
            floodFill(new boolean[this.board.length][this.board[0].length], X, Y);
        }
    }

    private void floodFill(boolean[][] visited, int X, int Y) {

        //quit if off the grid:
        if (X < 0 || X >= this.board.length || Y < 0 || Y >= this.board[0].length) return;

        //quit if visited:
        if (visited[X][Y]) return;
        visited[X][Y] = true;


        int numberOfMinesSurroundingTile = calculateNumberOfMinesSurroundingTile(X, Y);
        if (numberOfMinesSurroundingTile == 0) {
            board[X][Y] = new ExploredSafeCell();
        } else {
            if (numberOfMinesSurroundingTile == -1) {
                board[X][Y].StringRepresentation = "X";
            } else {
                board[X][Y].StringRepresentation = Integer.toString(numberOfMinesSurroundingTile);
            }
        }


        //recursively fill in all directions
        floodFill(visited, X + 1, Y);
        floodFill(visited, X - 1, Y);
        floodFill(visited, X, Y + 1);
        floodFill(visited, X, Y - 1);
    }

    public boolean doesCellContainMine(int X, int Y) {
        if (board[X][Y].getClass() == MarkedMine.class || board[X][Y].getClass() == Mine.class) {
            return true;
        }
        return false;
    }

    public int calculateNumberOfMinesSurroundingTile(int X, int Y) {
        int result = 0;

        if (this.board[X][Y].getClass() == Mine.class) {
            return -1;
        }

        int upShift = 1;
        int downShift = 1;
        int leftShift = 1;
        int rightShift = 1;

        if (X == 0) {
            upShift = 0;
        }
        if (X == this.board.length - 1) {
            downShift = 0;
        }
        if (Y == 0) {
            leftShift = 0;
        }
        if (Y == this.board.length - 1) {
            rightShift = 0;
        }
        for (int i = X - upShift; i <= X + downShift; i++) {
            for (int j = Y - leftShift; j <= Y + rightShift; j++) {
                if (this.board[i][j].getClass() == Mine.class || this.board[i][j].getClass() == MarkedMine.class) {
                    result++;
                }
            }
        }
        return result;
    }

    public boolean isGameOverByAllMinesCorrectlyMarkedAndNoMinesIncorrectlyMarked() {
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
        if (minesCorrectlyMarked == this.howManyMinesOnBoard) {
            if (minesIncorrectlyMarked == 0) {
                this.gameOver = true;
                return true;
            }
        }
        return false;
    }
}
