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
    static ArrayList<String> latestregistration = new ArrayList<>();

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

                case 3 -> cancelRegistration();

                case 4 -> displayParticipants();

                case 5 -> displayStats();

                case 6 -> searchParticipant();

                case 7 -> latestRegistration();
                
                case 8 -> report();

                default -> {
                    System.out.println("Invalid choice.");
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
                "7. Latest Registrations\n" +
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
        while(!input.hasNextInt()){
            System.out.println("Invalid input! Please enter a number.");
            input.next();
            System.out.print("Try again: ");
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
        // display categories
        for (int i = 0; i < category.length; i++){
            System.out.println((i+1) + ". " + category[i]);
        }
        System.out.print("Select a category: ");
        int catChoice = validateRange(1, 3) - 1;
        if (catChoice == -1) return;

        // display events
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
        String combined = name + "-" + ID;
        if (registrations[catChoice][eventChoice].contains(combined)) {
            System.out.println("Participant already registered for this event.");
            return;
        }
        registrations[catChoice][eventChoice].add(combined);

        //these lines are added for case 7
        latestregistration.add(name+" "+ID+" "+events[catChoice][eventChoice]);
        System.out.println("Registration is completed");
        System.out.println();
    } // end of registrations

    // method to cancel registration
    public static void cancelRegistration(){
        for (int i = 0; i < category.length; i++){
            System.out.println((i+1) + ". " + category[i]);
        }
        System.out.print("Select a category: ");
        int catChoice = validateRange(1, 3) - 1;
        if (catChoice == -1) return;

        System.out.println();
        // show event in category
        for (int i = 0; i < events[0].length; i++){
            System.out.println((i+1) + ". " + events[catChoice][i]);
        }
        System.out.print("Select an event: ");
        int eventChoice = validateRange(1, 4) - 1;
        if (eventChoice == -1) return;

        System.out.println();
        System.out.println("Search by:");
        System.out.println("1. Name");
        System.out.println("2. ID");
        System.out.print("Your choice: ");
        int searchChoice = validateRange(1, 2);
        if (searchChoice == 0) return;

        System.out.println();
        String searchValue = "";
        if (searchChoice == 1){
            System.out.print("Enter participant name: ");
            searchValue = input.nextLine().trim();
        } else {
            System.out.print("Enter participant ID: ");
            int idNum = validateInt(input);
            input.nextLine();
            searchValue = String.valueOf(idNum);
        }

        // search through the registration
        boolean found = false;
        ArrayList<String> eventList = registrations[catChoice][eventChoice];

        for (int i = 0; i < eventList.size(); i++){
            String participant = eventList.get(i);
            String[] parts = participant.split("-");
            String name = parts[0];
            String id = parts[1];

            // check if it matches
            if (searchChoice == 1){
                // searching by name
                if (name.equalsIgnoreCase(searchValue)){
                    eventList.remove(i);
                    System.out.println("Successfully removed: " + name + " (ID: " + id + ")");
                    System.out.println("From event: " + events[catChoice][eventChoice]);
                    found = true;
                    break;
                }
            } else {
                // searching by ID
                if (id.equals(searchValue)){
                    eventList.remove(i);
                    System.out.println("Successfully removed: " + name + " (ID: " + id + ")");
                    System.out.println("From event: " + events[catChoice][eventChoice]);
                    found = true;
                    break;
                }
            }
        }

        if (!found){
            System.out.println("Participant not found in this event.");
        }
        System.out.println();
    } // end of cancel registration

    public static void displayParticipants(){
        System.out.println("1. Display in ascending order");
        System.out.println("2. Display in descending order");
        System.out.println("How would you like the participants: ");
        int choice = validateRange(1, 2);
        if (choice == 0) return;

        for (int cat = 0; cat < registrations.length; cat++){
            System.out.println((cat+1) + ". " + category[cat]);
            for (int event = 0; event < registrations[0].length; event++){
                int counter = 1;
                ArrayList<String> participants = sort(cat, event, choice);
                System.out.println("    " + (cat+1) + "." + (event+1) + ". " + events[cat][event]);
                for (String participant: participants){
                    System.out.println("        " + (cat+1) + "." + (event+1) + "." + counter + ". " + participant);
                    counter++;
                }
            }
        }
        System.out.println();
    } // end of displayParticipants

    // sorting method
    public static ArrayList<String> sort(int category, int event, int pattern){
        // create a copy list of the names in the current event
        ArrayList<String> participants = new ArrayList<>(registrations[category][event]);
        if (pattern == 1){
            Collections.sort(participants);
        } else{
            Collections.sort(participants, Collections.reverseOrder());
        }
        return participants;
    } // end of sort

    // method to display stats
    public static void displayStats(){
        System.out.println("~~~~~~ display stats ~~~~~~");
        for (int i=0;i<category.length;i++){
            System.out.println("category: " + category[i]);
            int totcategory=0;// tracking the total number of registrations in current category
            for (int j=0;j<events[i].length;j++){
                int totevent=registrations[i][j].size();
                totcategory+=totevent;
                System.out.println(events[i][j]+": " + totevent + " participants");
            }
            System.out.println("total for " + category[i] + ":" + totcategory);
        }
        System.out.println();
    } // end of displayStats

    // Can search by participant name or ID
    public static void searchParticipant(){
        // choose how to search
        System.out.println("Search for a Participant");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Search by:");
        System.out.println("1. Name");
        System.out.println("2. ID");
        System.out.print("Your choice: ");
        int searchChoice = validateRange(1, 2);
        if (searchChoice == 0) return;

        System.out.println();
        String searchValue = "";
        if (searchChoice == 1){
            System.out.print("Enter participant name: ");
            searchValue = input.nextLine().trim();
        } else {
            System.out.print("Enter participant ID: ");
            int idNum = validateInt(input);
            input.nextLine();
            searchValue = String.valueOf(idNum);
        }

        System.out.println("Searching for: " + searchValue);
        System.out.println();

        // counter to see how many events they are in
        int foundCount = 0;

        // loop through categories
        for (int i = 0; i < category.length; i++){
            // loop through events in the category
            for (int j = 0; j < events[i].length; j++){
                // show participants for the event
                ArrayList<String> eventList = registrations[i][j];

                // search through all participants in the event
                for (int k = 0; k < eventList.size(); k++){
                    String participant = eventList.get(k);
                    String[] parts = participant.split("-");
                    String name = parts[0];
                    String id = parts[1];

                    // check if this participant matches our search
                    boolean matches = false;
                    if (searchChoice == 1){
                        // searching by name case insensitive
                        if (name.equalsIgnoreCase(searchValue)){
                            matches = true;
                        }
                    } else {
                        // searching by ID
                        if (id.equals(searchValue)){
                            matches = true;
                        }
                    }

                    // if match found
                    if (matches){
                        foundCount++;
                        System.out.println("Found in:");
                        System.out.println("Category: " + category[i]);
                        System.out.println("Event: " + events[i][j]);
                        System.out.println("Participant: " + name + " (ID: " + id + ")");
                        System.out.println();
                    }
                }
            }
        }
        // display results summary
        if (foundCount == 0){
            System.out.println("Participant not found in any events! ");
        } else {
            System.out.println("Total events registered: " + foundCount);
        }
        System.out.println();
    } // end of search participant

    // generates a report of popularity
    public static void report(){
        int mostCat = 0;
        int catIndex = 0;
        int mostEvent = 0;
        int[] eventIndex = new int[2];
        for (int i = 0; i < category.length; i++) {
            int totCategory = 0;// tracking the total number of registrations in current category
            for (int j = 0; j < events[i].length; j++) {
                int totEvent = registrations[i][j].size();
                totCategory += totEvent;
                if (totEvent > mostEvent){
                    mostEvent = totEvent;
                    eventIndex[0] = i;
                    eventIndex[1] = j;
                }
            }
            if (totCategory > mostCat){
                mostCat = totCategory;
                catIndex = i;
            }
        }

        System.out.println("The most popular category is '" + category[catIndex] + "' with " + mostCat + " participants.");
        System.out.println("And the most popular event is '" + events[eventIndex[0]][eventIndex[1]] + "' with " + mostEvent + " participants." );
        System.out.println();
    } // end of report
    // method to display the latest entries made first
    public static void latestRegistration() {
        System.out.println("~~~~~~ Latest Registration ~~~~~~");
        if (latestregistration.isEmpty()){
            System.out.println("registration is empty");
        }else {
            for (int i=latestregistration.size()-1;i>=0;i--){
                System.out.println(latestregistration.get(i));
            }
        }
    }
}