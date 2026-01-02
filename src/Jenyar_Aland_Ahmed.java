import java.util.*;
public class Jenyar_Aland_Ahmed {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        int choice = menu();
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
