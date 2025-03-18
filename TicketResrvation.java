class Ticket {
    int ticketID;
    String customerName, movieName;
    int seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null, tail = null;
    private int totalTickets = 0;

    public void addTicket(int ticketID, String customerName, String movieName, int seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            tail.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
        totalTickets++;
    }

    public void removeTicket(int ticketID) {
        if (head == null) return;
        Ticket current = head, prev = null;
        do {
            if (current.ticketID == ticketID) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = current.next;
                }
                totalTickets--;
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void displayTickets() {
        if (head == null) return;
        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName + ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    public void searchTicket(String searchKey) {
        if (head == null) return;
        Ticket current = head;
        do {
            if (current.customerName.equalsIgnoreCase(searchKey) || current.movieName.equalsIgnoreCase(searchKey)) {
                System.out.println("Found Ticket - ID: " + current.ticketID + ", Customer: " + current.customerName + ", Movie: " + current.movieName);
            }
            current = current.next;
        } while (current != head);
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}

public class TicketResrvation {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(101, "Alice", "Inception", 12, "10:00 AM");
        system.addTicket(102, "Bob", "Interstellar", 15, "1:00 PM");
        system.addTicket(103, "Charlie", "Dune", 18, "3:00 PM");
        
        System.out.println("All Tickets:");
        system.displayTickets();
        
        System.out.println("Searching for 'Interstellar':");
        system.searchTicket("Interstellar");
        
        System.out.println("Total Tickets: " + system.getTotalTickets());
        
        system.removeTicket(102);
        System.out.println("After Removing Ticket 102:");
        system.displayTickets();
    }
}
