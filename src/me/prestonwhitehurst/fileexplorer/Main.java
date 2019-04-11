package me.prestonwhitehurst.fileexplorer;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static Scanner keyboard;

    public static void main(String[] args) {

        FileExplorer fileExplorer = new FileExplorer();
        keyboard = new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            String userIndex;
            int userIndexInt;
            String option;

            fileExplorer.printCurrentDirectoryFiles();

            printMenu();

            System.out.print("Please choose an option: ");
            option = keyboard.nextLine();

            switch(option) {

                case "0" :
                    File[] s = fileExplorer.getCurrentDirectoryFiles();
                    System.out.print("Enter an index: ");
                    userIndex = keyboard.nextLine();
                    try {
                        userIndexInt = Integer.parseInt(userIndex);
                        fileExplorer.addDirectory(s[userIndexInt]);
                    } catch(NumberFormatException e) {
                            System.out.println("Error: Entered data is not an index.\n");
                    }
                    System.out.println();
                    break;

                case "1" :
                    fileExplorer.removeLastDirectory();
                    fileExplorer.resetCurrentDirectoryFiles();
                    System.out.println();
                    break;

                case "2" :
                    s = fileExplorer.getCurrentDirectoryFiles();
                    System.out.print("Enter an index: ");
                    userIndex = keyboard.nextLine();
                    try {
                        userIndexInt = Integer.parseInt(userIndex);
                        fileExplorer.openFile(s[userIndexInt]);
                    }catch (NumberFormatException e) {
                        System.out.println("Error: Entered data is not an index.\n");
                    }
                    System.out.println();
                    break;

                case "3" :
                    loop = false;
                    break;

                default :
                    System.out.println();
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("/////////////MENU////////////\n");
        System.out.println("(0) Go into a folder");
        System.out.println("(1) Go back");
        System.out.println("(2) Open a file");
        System.out.println("(3) Quit the program\n");
    }
}
