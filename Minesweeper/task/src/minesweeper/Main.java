package minesweeper;

public class Main {
    public static void main(String[] args) {
        int numMines = UserInputUtils.get_HowManyMinesDoYouWantToPlace();
        Board board = new Board(9, numMines);
        board.displayBoard();


        while (true) {
            String userInput = UserInputUtils.getStringUserInput();
            int X = UserInputUtils.getXfromStringUserInput(userInput);
            int Y = UserInputUtils.getYfromStringUserInput(userInput);
            String action = UserInputUtils.getActionFromStringUserInput(userInput);
            if (action.equals("mine")) {
                board = mineAction(board, X, Y);
            } else if (action.equals("free")) {
                board = freeAction(board, X, Y);
            }

            board.displayBoard();

            if (board.isGameOverByAllMinesCorrectlyMarkedAndNoMinesIncorrectlyMarked()) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }
    }

    private static Board mineAction(Board board, int X, int Y) {
        if (board.board[X][Y].getClass() == SafeCell.class && (!board.board[X][Y].StringRepresentation.equals("."))) {
            System.out.println("There is a number here!");
        } else if (board.board[X][Y].getClass() == SafeCell.class && board.board[X][Y].StringRepresentation.equals(".")) {
            board.board[X][Y] = new MarkedSafeCell();
        } else if (board.board[X][Y].getClass() == MarkedSafeCell.class && board.board[X][Y].StringRepresentation.equals("*")) {
            board.board[X][Y] = new SafeCell();
        } else if (board.board[X][Y].getClass() == Mine.class) {
            board.board[X][Y] = new MarkedMine();
        } else if (board.board[X][Y].getClass() == MarkedMine.class) {
            board.board[X][Y] = new Mine();
        }
        return board;
    }

    private static Board freeAction(Board board, int X, int Y) {
        board.exploreCell(X, Y);
        board.numberOfFreeTurns++;
        return board;
    }
}
