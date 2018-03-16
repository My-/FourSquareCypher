package ie.gmit.v3;

import java.util.Scanner;


enum MenuPick {DECRYPT, ENCRYPT, EXIT}

public class Menu {

    static Scanner in = new Scanner(System.in);

    static String mainMenu = "\n" +
            "=====================\n" +
            "=  4 Square FourSquareCypher  =\n" +
            "=====================\n\n" +
            "1 - Encrypt.\n" +
            "2 - Decrypt.\n" +
            "3 - Exit.\n\n" +
            "\tYour pick: ";

    static String getFileNameMenu = "\n" +
            "=====================\n" +
            "=  Enter file name  =\n" +
            "=====================\n\n" +
            "file: ";


    public static void main(String[] args) {
//        System.out.println(
//                " \u256d\u256e\u256d\u256e\n" +
//                " \u2502\u2570\u256f\n" +
//                " \u2570\u25dd\n" +
//                " \u2573\u2573\u2573\u2573\n" +
//                " \u2573\u2573\u2573\u2573  4 square cypher \n" +
//                " \u2573\u2573\u2573\u2573\n"
//        );

        mainMenu();
    }

    static MenuPick mainMenu(){
        MenuPick R = MenuPick.EXIT;
        int pick;

        do{
            System.out.println(mainMenu);
            pick = Integer.parseInt(in.next()); // ?? do validation ? if number ?
        }while(pick < 1 || 3 < pick);

        switch (pick){
            case 1: R = MenuPick.ENCRYPT; break;
            case 2: R = MenuPick.DECRYPT; break;
        }

        return R;
    }

    static String getFileName(){
        System.out.println(getFileNameMenu);
        return in.next();
    }
}
