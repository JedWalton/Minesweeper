package minesweeper;

public class Main {
    public static void main(String[] args) {
        int numMines = UserInputUtils.get_HowManyMinesDoYouWantToPlace();
        Board board = new Board(9, numMines);
        board.displayBoard();


        while (true){
            String coords = UserInputUtils.get_setDeleteMinesMarksXAndYCoordinates();
            int X = UserInputUtils.getXfromStringCoordinates(coords);
            int Y = UserInputUtils.getYfromStringCoordinates(coords);

            if(board.board[X][Y].getClass() == SafeCell.class && (!board.board[X][Y].StringRepresentation.equals("."))) {
                System.out.println("There is a number here!");
            } else if (board.board[X][Y].getClass() == SafeCell.class && board.board[X][Y].StringRepresentation.equals(".")) {
                board.board[X][Y] = new MarkedSafeCell();
            } else if (board.board[X][Y].getClass() == MarkedSafeCell.class && board.board[X][Y].StringRepresentation.equals("*")) {
                board.board[X][Y] = new SafeCell();
            } else if(board.board[X][Y].getClass() == Mine.class) {
                board.board[X][Y] = new MarkedMine();
            }
            board.displayBoard();

            if(board.isGameOver()) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }


    }
}
