import java.util.InputMismatchException;
import java.util.Scanner;

public class Minesweeper {


    public static void main(String[] args) {
        Minesweeper game = new Minesweeper();
        game.createField();
        game.play();
    }


    /** createField method of type void
     *Takes information from the user about difficulty level
     * Builds the Minesweeper field according to received data
     * After receiving information about the first move of the player, the loadField method is called (described in Field.java)
     */

    public void createField(){
        System.out.println("Enter the Difficulty Level");
        System.out.println("Press 0 for BEGINNER (9*9 Cells and 10 Mines)");
        System.out.println("Press 1 fo INTERMEDIATE (16*16 Cells and 40 Mines)");
        System.out.println("Enter 2 for ADVANCED (24*24 Cells and 99 Mines)");
        int diff = -1;
        size = 0;
        mines =0;

        while(diff==-1) {
            try {
                diff = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scan.next();
            }
        }

        while(size==0) {
            switch (diff) {
                case 0:
                    size = 9;
                    mines =10;
                    break;
                case 1:
                    size =  16;
                    mines = 40;
                    break;
                case 2:
                    size = 24;
                    mines = 99;
                    break;
                default:
                    System.out.println("Invalid input.");
                    diff = scan.nextInt();
                    break;
            }
        }

        field = new Field(size, mines);
        System.out.println("Current status of board:");
        field.showField();

        System.out.println();
        System.out.println("Enter your move, (row, column)");

        enterCoordinates();

        field.loadField(x,y);
        field.showField();
    }

    /** The plat method of type void
        * Receives data from the user about the next move of the player
        * Calls the playNext method from class Field (described in Field.java)
        * The player will make a new move until he has stepped on all safe points and wins  or until he steps on a mine and loses
        * If the data about the point is invalid, or the player has already "stepped" on the point, data should be re-entered (using the enter coordinatesMethod)
     */

    public void play(){
        int i=1;
        while(i<size*size-mines){
            System.out.println();
            System.out.println("Enter your move, (row, column)");

            enterCoordinates();

            int check =  field.playNext(x,y);
            field.showField();
            switch(check){
                case -1:
                    System.out.println();
                    System.out.println("Invalid input");
                    break;
                case 0:
                    i++;
                    break;
                case 1:
                    System.out.println();
                    System.out.println("You lost!");
                    i=size*size+1;
                    break;
            }
        }
        if(i==size*size- mines){
            System.out.println("You won!");
        }
    }

    /**
     * The enterCoordinates method of type void
     * Secures data input using InputMismatchException and the checkCoordinates method from class Field (throws own exception type)
     * When an exception has been caught, the user receives information about what caused the error
     */

    private void enterCoordinates(){
        x=-1;
        y=-1;

        while(x==-1 || y==-1) {
            try {

                x = scan.nextInt();
                y = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                scan.next();
                continue;
            }

            try{
                field.checkCoordinates(x,y);
            }catch(ValueException e){
                x=-1;
                y=-1;
                System.out.println();
                System.out.println("Invalid input - you are outside the playing field");

            }
        }
    }

    /**
     * As it follows:
     * field - The Minesweeper field - an object of class Field described in Field.java
     * size - the size of the field (as the length and the width are the same, only one variable is needed )
     * mines - the number of mines on the field
     * scan - input stream object to extract information from the user
     */

    int x;
    int y;
    Field field;
    int size;
    int mines;
    Scanner scan = new Scanner(System.in);
}


