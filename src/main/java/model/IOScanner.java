package src.main.java.model;

import java.util.Scanner;

public class IOScanner {
    //initializing the scanner in this class
    static Scanner input = new Scanner(System.in);

    //This creates a function we can call from the main class ex. ioScanner.nextInt() and read and return the input. It also print an output message that is given when the method is called
    public static int nextInt(String message){
        System.out.print(message);
        return input.nextInt();
    }
    //this works exactly like the .nextInt(); but it directly clears the keyboard buffer so you dont have to put input.nextLine(); after reading an int.
    public static int nextIntCLR_BUFF(String message){
        System.out.print(message);
        int temp = input.nextInt();
        input.nextLine();
        return temp;
    }
    //reads the nextLine while also directly being able to print an output.
    public static String nextLine(String message){
        System.out.print(message);
        return input.nextLine();
    }
    //Closes the scanner
    public static void close(){
        input.close();
    }
}

