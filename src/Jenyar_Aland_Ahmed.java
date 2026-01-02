import java.util.*;
public class Jenyar_Aland_Ahmed {
    static Scanner input = new Scanner(System.in);
    // counter array to store how many participants in each event/category
    int[][] eventCounts = new int[3][4];
    // single dimension array to store the categories
    static String[] category = {"Music", "Art Exhibitions", "Literature & Poetry"};
    // two dimension array to store the events as [category][event]
    static String[][] event = {
            {"Zagros Mountain Melodies", "Golden Daf Rhythms", "Hewler Folk Night", "Slemani Strings Festival"},
            {"Colors of the Citadel", "Modern Kurdish Canvas", "Heritage Textile Showcase", "Landscapes of Spring"},
            {"Echoes of Nali", "Contemporary Kurdish Voices", "Legends of the Ancients", "The Poets' Circle"}
    };


    public static void main(String[] args){

        int choice = menu();

        switch (choice){
            case 0 ->{
                System.out.println("Exiting.");
                System.exit(0);
            } // exit

            default -> {
                System.out.println("Choice is yet to be implemented");
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
        return input.nextInt();
    }
}
