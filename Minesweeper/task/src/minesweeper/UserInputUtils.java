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

    static String get_setDeleteMinesMarksXAndYCoordinates(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set/delete mines marks (x and y coordinates):");
        return scanner.nextLine();
    }

    static int getXfromStringCoordinates(String coords) {
        String x = String.valueOf(coords.charAt(2));
        return Integer.parseInt(x) - 1;
    }

    static int getYfromStringCoordinates(String coords) {
        String y = String.valueOf(coords.charAt(0));
        return Integer.parseInt(y) - 1;
    }


}
