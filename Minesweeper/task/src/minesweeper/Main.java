package minesweeper;

public class Main {
    public static void main(String[] args) {
        // write your code here

        int numMines = Menu.getHowManyMinesDoYouWantToPlace();
        Board board = new Board(9, numMines);
        board.displayBoard();
    }
}
