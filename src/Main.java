import java.util.Scanner;

public class Main {

    // Variables and Arrays
    private static final Scanner scanner = new Scanner(System.in);
    private static final Hotel hotel = new Hotel();
    private static final Queue queue = new Queue();
    private static int roomNum = 0;
    private static String firstName;
    private static String surname;
    private static String creditNum;
    private static int guestsNum;
    private static boolean isNumber;

    public static void main(String[] args) {
        // Beginning of program
        boolean quit = false;
        printActions();
        String action;
        hotel.initialise();

        // While loop to run program until user decides to end the program
        while (!quit) {

            System.out.println("\nEnter action: (P to show available actions)");
            action = scanner.next().toUpperCase();

            switch (action) {

                case "A":
                    addCustomer();
                    break;
                case "V":
                    hotel.displayRooms();
                    break;
                case "E":
                    hotel.emptyRooms();
                    break;
                case "D":
                    deleteCustomer();
                    break;
                case "F":
                    queryRoom();
                    break;
                case "S":
                    hotel.storeData();
                    break;
                case "L":
                    hotel.loadData();
                    break;
                case "O":
                    hotel.viewGuests();
                    break;
                case "Q":
                    System.out.println("\nProgram ended");
                    quit = true;
                    break;
                default:
                    System.out.println("\nPlease choose one of the options");
                    printActions();
            }
        }
    }

    // User input for adding customer
    private static void addCustomer() {

        // Checks if all rooms have been booked. If so then add the next customer to the waiting list
        if (hotel.roomCheck() == 8) {
            // User input for adding next customer to the waiting list
            System.out.println("There are no more rooms available\n" +
                    "please give your name to be added to the waiting list");

            System.out.println("Enter your first name:");
            firstName = scanner.next().toLowerCase();
            scanner.nextLine();

            System.out.println("Enter your surname:");
            surname = scanner.next().toLowerCase();
            scanner.nextLine();

            // Validates credit card input to only receive an integer
            do {
                System.out.println("Enter your credit card number:");
                if (scanner.hasNextInt()) {
                    creditNum = scanner.next();
                    scanner.nextLine();
                    isNumber = true;
                } else {
                    System.out.println("Enter a valid credit card number");
                    isNumber = false;
                    scanner.next();
                }
            } while (!(isNumber));

            queue.addQueue(firstName, surname, creditNum);
        } else {
            // Asks user for room number and number of guests
            hotel.displayRooms();

            // Validates room input to only receive an integer
            do {
                System.out.println("\nWhich room would you like to book:");
                if (scanner.hasNextInt()) {
                    roomNum = scanner.nextInt();
                    scanner.nextLine();
                    isNumber = true;
                } else {
                    System.out.println("please enter a number");
                    isNumber = false;
                    scanner.next();
                }
            } while (!(isNumber) || (roomNum > 7) || (roomNum < 0));

            // Validates quests input to only receive an integer
            do {
                System.out.println("How many guests would you like to book for:");
                if (scanner.hasNextInt()) {
                    guestsNum = scanner.nextInt();
                    scanner.nextLine();
                    isNumber = true;
                } else {
                    System.out.println("Please enter a number");
                    isNumber = false;
                    scanner.next();
                }
            } while (!(isNumber));

            // Validates the number of guests that can be added
            if (guestsNum < 0) {
                System.out.println("A minimum of 0 guests needs to be entered");
            } else {
                // Asks user for personal information
                System.out.println("Enter your first name:");
                firstName = scanner.next().toLowerCase();
                scanner.nextLine();

                System.out.println("Enter your surname:");
                surname = scanner.next().toLowerCase();
                scanner.nextLine();

                // Validates the credit card number to only receive an integer
                do {
                    System.out.println("Enter your credit card number:");
                    if (scanner.hasNextInt()) {
                        creditNum = scanner.next();
                        scanner.nextLine();
                        isNumber = true;
                    } else {
                        System.out.println("Enter a valid credit card number");
                        isNumber = false;
                        scanner.next();
                    }
                } while (!(isNumber));

                // Checks if room is empty. Customer gets added to array if room is empty
                if (hotel.addCustomer(roomNum, firstName, surname, creditNum)) {
                    if (guestsNum == 0) {
                        System.out.println("Successfully booked " + firstName + " " + surname +
                                " for room " + roomNum);
                    } else {
                        System.out.println("Successfully booked " + firstName + " " + surname +
                                " for room " + roomNum + " with " + guestsNum + " guests");
                    }
                } else {
                    System.out.println("room " + roomNum + " is already booked\n");
                }
            }
        }
    }

    // Removes customer from array
    private static void deleteCustomer() {

        hotel.takenRooms();

        // Validates room number input to receive an integer
        do {
            System.out.println("Enter the room number you would like to clear out:");
            if (scanner.hasNextInt()) {
                roomNum = scanner.nextInt();
                scanner.nextLine();
                isNumber = true;
            } else {
                System.out.println("Enter a valid room number");
                isNumber = false;
                scanner.next();
            }
        } while (!(isNumber) || (roomNum > 7) || (roomNum < 0));

        // Clears room and replaces old customer with the next customer in the waiting list
        if ((hotel.roomCheck() == 8) && (!queue.isEmpty())) {
            System.out.println("room " + roomNum + " has been cleared");
            queue.takeQueue(roomNum);
        } else {
            // Checks whether room is already empty. If not then customer gets removed
            if (hotel.deleteCustomer(roomNum)) {
                System.out.println("room " + roomNum + " has been cleared");
            } else {
                System.out.println("Room " + roomNum + " is already empty");
            }
        }
    }

    // Finds the room of a searched customer
    private static void queryRoom() {
        System.out.println("Enter your first name:");
        firstName = scanner.next().toLowerCase();
        scanner.nextLine();

        System.out.println("Enter your surname:");
        surname = scanner.next().toLowerCase();
        scanner.nextLine();

        //Gets the room number of the searched customer. Returns -1 if not found
        roomNum = hotel.queryRoom(firstName, surname);

        if (roomNum >= 0) {
            System.out.println(firstName + " " + surname + " is in room " + roomNum);
        } else {
            System.out.println(firstName + " " + surname + " has not booked a room");
        }
    }

    // Displays all available options
    private static void printActions() {

        System.out.println("\n available actions:\npress:");
        System.out.println(
                """
                        A - to add customer to room
                        V - to view all rooms
                        E - display empty rooms
                        D - delete customer from room
                        F - find room from customer name
                        S - store program data into file
                        L - load program data from file
                        O - view guests ordered alphabetically by name
                        Q - exit program""");
    }
}