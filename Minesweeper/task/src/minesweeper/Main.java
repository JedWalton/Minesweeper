package minesweeper;

public class Main {
    public static void main(String[] args) {
        int numMines = Menu.getHowManyMinesDoYouWantToPlace();
        Board board = new Board(9, numMines);
        board.displayBoard();
    }
}
