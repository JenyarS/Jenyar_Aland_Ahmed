import java.util.*;
public class Jenyar_Aland_Ahmed {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        // single dimension array to store the categories
        String[] category = {"Music", "Art Exhibitions", "Literature & Poetry"};
        // two dimension array to store the events as [category][event]
        String[][] event = {
                {"cat1Event1", "cat1Event2"},
                {"cat2Event1", "cat2Event2"},
                {"cat3Event1", "cat3Event2"}
        };

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
