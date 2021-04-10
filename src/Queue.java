public class Queue {

    // Arrays
    // qItems - first name
    // qItems2 - Surname
    // qItems3 - Credit card number
    String[] qItems = new String[8];
    String[] qItems2 = new String[8];
    String[] qItems3 = new String[8];

    // Variables for arrays
    int front = -1, end = -1, currentSize = 0;

    // Adds customer to a queue if queue is not full
    public void addQueue(String firstName, String surname, String creditCard) {

        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            System.out.println(firstName + " " + surname +  " has been added to the queue");
            end = (end + 1) % qItems.length;
            qItems[end] = firstName;
            qItems2[end] = surname;
            qItems3[end] = creditCard;
            currentSize++;

            if (front == -1) {
                front++;
            }
        }
    }

    // Removes customer from queue if queue is not empty
    public void takeQueue(int roomNum) {

        if (isEmpty()) {

            System.out.println("Queue is empty");
        } else {
            System.out.println(qItems[front] + " " + qItems2[front] + " has been added to room " + roomNum);
            Hotel.myHotel[roomNum].setFirstName(qItems[front]);
            Hotel.myHotel[roomNum].setSurname(qItems2[front]);
            Hotel.myHotel[roomNum].setCreditCard(qItems3[front]);

            // Resets customer information
            qItems[front] = null;
            qItems2[front] = null;
            qItems3[front] = null;
            front = (front + 1) % qItems.length;
            currentSize--;

        }
    }

    // Checks if queue is empty
    public boolean isEmpty() {

        return currentSize == 0;
    }

    // Checks if queue is full
    private boolean isFull() {

        return currentSize == qItems.length;
    }
}
