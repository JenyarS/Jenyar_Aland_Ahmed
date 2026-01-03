import java.util.*;

public class Jenyar_Aland_Ahmed {
    static Scanner input = new Scanner(System.in);
    // single dimension array to store the categories
    static String[] category = {"Music", "Art Exhibitions", "Literature & Poetry"};
    // two dimension array to store the events as [category][event]
    static String[][] events = {
            {"Zagros Mountain Melodies", "Golden Daf Rhythms", "Hewler Folk Night", "Slemani Strings Festival"},
            {"Colors of the Citadel", "Modern Kurdish Canvas", "Heritage Textile Showcase", "Landscapes of Spring"},
            {"Echoes of Nali", "Contemporary Kurdish Voices", "Legends of the Ancients", "The Poets' Circle"}
    };
    // arraylist to store the participant information
    static ArrayList<String>[][] registrations = new ArrayList[3][4];

    // we need this method to initialize the arraylist
    public static void initializeSystem(){
        for (int i = 0; i < registrations.length; i++){ // categories
            for (int j = 0; j < registrations[0].length; j++){ // events
                // participants list
                registrations[i][j] = new ArrayList<String>();
            }
        }
    } // end of initializeSystem

    public static void main(String[] args){
        initializeSystem();
        for (;;) {
            int choice = menu();
            input.nextLine(); // consume newline
            System.out.println();

            switch (choice) {
                case 0 -> {
                    System.out.println("Exiting.");
                    System.exit(0);
                } // exit

                case 1 -> viewEvents();

                case 2 -> register();

                default -> {
                    System.out.println("Choice is yet to be implemented");
                }
            }
        }
    } // end of main

    public static int menu(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~ Event Registration ~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println(
                "1. View Events\n" +
                "2. Register for an event\n" +
                "3. Cancel Registration\n" +
                "4. Display all Participants\n" +
                "5. Display Number of Participants\n" +
                "6. Search for a Participant\n" +
                "7. Lastest Registrations\n" +
                "8. Popularity Report\n" +
                "0. Exit\n"
        );
        System.out.print("What would you like to do? ");
        return validateInt(input);
    } // end of menu

    public static void viewEvents(){
        for (int i = 0; i < category.length; i++){
            System.out.println((i+1) + ". " + category[i]);
        }
        System.out.print("Select a category: ");

        int catChoice = validateRange(1, 3) - 1;
        if (catChoice == -1) return;

        System.out.println();
        System.out.println("Current Events:");
        for (int i = 0; i < events[0].length; i++){
            System.out.println((i+1) + ". " + events[catChoice][i]);
        }
        System.out.println();
    } // end of viewEvents

    // method to validate if an integer is input
    public static int validateInt(Scanner input){
        int tries = 3;
        while(!input.hasNextInt()){
            if (tries == 0){
                System.out.println("Too many failed attempts.");
                return 0;
            }
            System.out.println("Invalid input! Please enter a number.");
            input.next();
            System.out.print("Try again: ");
            tries--;
        }
        return input.nextInt();
    } // end of validateInt

    // method to validate the input integer fits withing the acceptable range
    public static int validateRange(int min, int max){
        int num;
        boolean error = false;
        int tries = 3;
        do {
            if (tries == 0) {
                System.out.println("Too many failed attempts.");
                return 0;
            }
            if (error){
                System.out.print("Invalid choice, try again: ");
            }
            num = validateInt(input);
            input.nextLine(); // consume newline
            error = true;
            tries--;
            System.out.println();
        }while (num < min || num > max);
        return num;
    } // end of validateRange

    public static void register(){
        for (int i = 0; i < category.length; i++){
            System.out.println((i+1) + ". " + category[i]);
        }
        System.out.print("Select a category: ");
        int catChoice = validateRange(1, 3) - 1;
        if (catChoice == -1) return;

        System.out.println();
        for (int i = 0; i < events[0].length; i++){
            System.out.println((i+1) + ". " + events[catChoice][i]);
        }
        System.out.print("Select an event: ");
        int eventChoice = validateRange(1, 4) - 1;
        if (eventChoice == -1) return;

        System.out.println();
        System.out.print("Enter the participants name: ");
        String name = input.nextLine();
        System.out.print("Enter the participants ID: ");
        int ID = validateInt(input);
        input.nextLine(); // consume newline

        // this saves the name and ID together as name&id
        // which can later be split for searching
        String combined = name + "&" + ID;
        registrations[catChoice][eventChoice].add(combined);
    } // end of registrations
}