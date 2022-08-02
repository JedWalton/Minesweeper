package minesweeper;

import java.util.Scanner;

public class UserInputUtils {
    private UserInputUtils(){

    }

    static int get_HowManyMinesDoYouWantToPlace() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field?");
        return scanner.nextInt();
    }

    static String getStringUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set/unset mine marks or claim a cell as free:");
        return scanner.nextLine();
    }

    static int getXfromStringUserInput(String userInput) {
        String x = String.valueOf(userInput.charAt(2));
        return Integer.parseInt(x) - 1;
    }

    static int getYfromStringUserInput(String userInput) {
        String y = String.valueOf(userInput.charAt(0));
        return Integer.parseInt(y) - 1;
    }

    public static String getActionFromStringUserInput(String userInput) {
        return userInput.substring(4, 8);
    }
}
