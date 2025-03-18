class BookNode {
    int bookId;
    String title;
    String author;
    String genre;
    boolean isAvailable;
    BookNode next;
    BookNode prev;

    BookNode(int bookId, String title, String author, String genre, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private BookNode head;
    private BookNode tail;
    private int bookCount;

    // Add book at the beginning
    public void addBookAtBeginning(int bookId, String title, String author, String genre, boolean isAvailable) {
        BookNode newNode = new BookNode(bookId, title, author, genre, isAvailable);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        bookCount++;
    }

    // Add book at the end
    public void addBookAtEnd(int bookId, String title, String author, String genre, boolean isAvailable) {
        BookNode newNode = new BookNode(bookId, title, author, genre, isAvailable);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        bookCount++;
    }

    // Remove a book by ID
    public void removeBookById(int bookId) {
        BookNode temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }
        if (temp == null) return;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp == head) head = temp.next;
        if (temp == tail) tail = temp.prev;
        bookCount--;
    }

    // Search for a book by title or author
    public void searchBook(String key) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(key) || temp.author.equalsIgnoreCase(key)) {
                System.out.println("Found: " + temp.title + " by " + temp.author + " (ID: " + temp.bookId + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found!");
    }

    // Update book availability status
    public void updateAvailability(int bookId, boolean isAvailable) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                return;
            }
            temp = temp.next;
        }
    }

    // Display all books in forward order
    public void displayBooksForward() {
        BookNode temp = head;
        while (temp != null) {
            System.out.println(temp.bookId + ": " + temp.title + " by " + temp.author + " | Genre: " + temp.genre + " | Available: " + temp.isAvailable);
            temp = temp.next;
        }
    }

    // Display all books in reverse order
    public void displayBooksReverse() {
        BookNode temp = tail;
        while (temp != null) {
            System.out.println(temp.bookId + ": " + temp.title + " by " + temp.author + " | Genre: " + temp.genre + " | Available: " + temp.isAvailable);
            temp = temp.prev;
        }
    }

    // Count total books
    public int countBooks() {
        return bookCount;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBookAtEnd(101, "The Hobbit", "J.R.R. Tolkien", "Fantasy", true);
        library.addBookAtBeginning(102, "1984", "George Orwell", "Dystopian", true);
        library.addBookAtEnd(103, "To Kill a Mockingbird", "Harper Lee", "Classic", false);
        
        System.out.println("Library Books (Forward Order):");
        library.displayBooksForward();
        
        System.out.println("\nTotal Books: " + library.countBooks());
        
        System.out.println("\nSearching for '1984':");
        library.searchBook("1984");
        
        System.out.println("\nUpdating availability of book ID 103...");
        library.updateAvailability(103, true);
        library.displayBooksForward();
        
        System.out.println("\nRemoving book ID 101 (The Hobbit)...");
        library.removeBookById(101);
        library.displayBooksForward();
    }
}
