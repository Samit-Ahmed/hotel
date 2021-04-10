import java.io.*;
import java.util.Scanner;

public class Hotel {

    // Variables and Arrays
    static Room[] myHotel;
    Room[] orderedArray;
    String roomName;

    // Constructor
    public Hotel() {
        myHotel = new Room[8];
        this.orderedArray = new Room[myHotel.length];
        this.roomName = "";
    }

    // Initialises all arrays
    public void initialise() {
        for (int i = 0; i < myHotel.length; i++) {
            myHotel[i] = new Room();
        }

        for (int i = 0; i < orderedArray.length; i++) {
            orderedArray[i] = new Room();
        }

        for (Room value : myHotel) {
            value.setFirstName("e");
            value.setSurname("e");
        }

        for (Room room : orderedArray) {
            room.setFirstName("e");
        }
    }

    // Checks whether a room has already been booked by another customer
    public boolean addCustomer(int room, String firstName, String surname, String creditCard) {
        if (!myHotel[room].getFirstName().equals("e") && (!myHotel[room].getSurname().equals("e"))) {
            return false;
        } else {
            myHotel[room].setFirstName(firstName);
            myHotel[room].setSurname(surname);
            myHotel[room].setCreditCard(creditCard);
            return true;
        }
    }

    // Displays all rooms
    public void displayRooms() {
        for (int i = 0; i < myHotel.length; i++) {
            if (myHotel[i].getFirstName().equals("e")) {
                System.out.println("Room " + (i) + " is empty");
            } else {
                System.out.println("Room " + (i) + " is occupied by " + myHotel[i].getFirstName() + " " +
                        myHotel[i].getSurname());
            }
        }
    }

    // Displays all empty rooms
    public void emptyRooms() {
        for (int i = 0; i < myHotel.length; i++) {
            if (myHotel[i].getFirstName().equals("e")) {
                System.out.println("Room " + (i) + " is empty");
            }
        }
    }

    // Displays all rooms that have been booked
    public void takenRooms() {
        System.out.println("List of rooms that can be cleared out:\n");
        for (int i = 0; i < myHotel.length; i++) {
            if (!myHotel[i].getFirstName().equals("e")) {
                System.out.println("Room " + (i) + " is booked by " + myHotel[i].getFirstName() + " "
                + myHotel[i].getSurname());
            }
        }
    }

    // Returns the number of rooms that have been booked
    public int roomCheck() {
        int count = 0;
        for (Room room : myHotel) {
            if (!room.getFirstName().equals("e") && (!room.getSurname().equals("e"))) {
                count++;
            }
        }
        return count;
    }

    // Checks whether a room is empty. If not then removes the customer from the room
    public boolean deleteCustomer(int room) {
        if (myHotel[room].getFirstName().equals("e") && (myHotel[room].getSurname().equals("e"))) {
            return false;
        } else {
            myHotel[room].setFirstName("e");
            myHotel[room].setSurname("e");
            myHotel[room].setCreditCard(null);
            return true;
        }
    }

    // Checks whether a searched customer exists in the room
    public int queryRoom(String firstName, String surname) {

        return findCustomer(firstName, surname);
    }

    // Returns the room number of a found customer. Otherwise returns -1 if customer not found
    private int findCustomer(String firstName, String surname) {
        for (int i=0; i<myHotel.length; i++) {
            if (myHotel[i].getFirstName().equals(firstName) && (myHotel[i].getSurname().equals(surname))) {
                return i;
            }
        }
        return -1;
    }

    // Stores all customers details in a text file
    public void storeData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("customerInfo.txt"));
            for (Room room : myHotel) {
                bw.write(room.getFirstName() + " " + room.getSurname() + " " + room.getCreditCard() + "\n");
            }
            bw.close();
            System.out.println("Successfully saved data");
        } catch (Exception e) {
            System.out.println("\nError saving file");
        }
    }

    // Loads all customers details from a text file
    public void loadData() {
        Scanner x;
        int count = 0;

        try {
            x = new Scanner(new File("customerInfo.txt"));
            while (x.hasNext()) {
                String loadFirst = x.next();
                String loadSurname = x.next();
                String loadCreditCard = x.next();
                myHotel[count].setFirstName(loadFirst);
                myHotel[count].setSurname(loadSurname);
                myHotel[count].setCreditCard(loadCreditCard);
                count++;

            }
            System.out.println("Successfully loaded file");
        }
        catch (Exception e) {
            System.out.println("Could not find file");
        }
    }

    // Displays all customers in alphabetical order
    public void viewGuests() {
        String temp;

        for (int i=0; i<myHotel.length; i++) {
            orderedArray[i].setFirstName(myHotel[i].getFirstName() + " " + myHotel[i].getSurname());
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7 - i; j++) {
                if (orderedArray[j].getFirstName().compareTo((orderedArray[j + 1]).getFirstName()) > 0) {
                    temp = orderedArray[j].getFirstName();
                    orderedArray[j].setFirstName(orderedArray[j + 1].getFirstName());
                    orderedArray[j + 1].setFirstName(temp);
                }
            }
        }
        System.out.println("\nList of guests ordered alphabetically\n");
        for (Room s : orderedArray) {
            if (!s.getFirstName().equals("e " + "e")) {
                System.out.println(s.getFirstName() + " " + s.getSurname());
            }
        }
    }
}

