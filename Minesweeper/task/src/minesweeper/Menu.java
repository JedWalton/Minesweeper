package minesweeper;

import java.util.Scanner;

public class Menu {
    private Menu(){

    }

    static int getHowManyMinesDoYouWantToPlace() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field?");
        return scanner.nextInt();
    }
}
