class MovieNode {
    String title;
    String director;
    int year;
    double rating;
    MovieNode next, prev; // Double trouble: one for forward, one for backward!

    MovieNode(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = this.prev = null; // No friends yet!
    }
}

class MovieDoublyLinkedList {
    private MovieNode head, tail; // The king and queen of our movie kingdom!

    // Insert at the beginning - Because some movies deserve the spotlight first!
    public void addMovieAtBeginning(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (head == null) { // Lonely list? Not anymore!
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Insert at the end - Because some movies are meant for the grand finale!
    public void addMovieAtEnd(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Insert at a specific position - Because some movies like a custom slot!
    public void addMovieAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 0) {
            addMovieAtBeginning(title, director, year, rating);
            return;
        }
        MovieNode newNode = new MovieNode(title, director, year, rating);
        MovieNode temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addMovieAtEnd(title, director, year, rating);
            return;
        }
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next.prev = newNode;
        temp.next = newNode;
    }

    // Remove a movie by title - Because some movies must go off the list!
    public void removeMovieByTitle(String title) {
        MovieNode temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) return; // Movie not found? Sad!
        if (temp == head) head = head.next;
        if (temp == tail) tail = tail.prev;
        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
    }

    // Search for a movie by Director or Rating - Because we need to find hidden gems!
    public void searchMovie(String director, double rating) {
        MovieNode temp = head;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director) || temp.rating == rating) {
                System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            }
            temp = temp.next;
        }
    }

    // Update movie rating by title - Because ratings change faster than moods!
    public void updateMovieRating(String title, double newRating) {
        MovieNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    // Display all movies forward - Because scrolling down is fun!
    public void displayMoviesForward() {
        MovieNode temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.next;
        }
    }

    // Display all movies in reverse - Because sometimes looking back is nostalgic!
    public void displayMoviesReverse() {
        MovieNode temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.prev;
        }
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieDoublyLinkedList movieList = new MovieDoublyLinkedList();
        
        movieList.addMovieAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        movieList.addMovieAtBeginning("Titanic", "James Cameron", 1997, 7.8);
        movieList.addMovieAtPosition(1, "Avatar", "James Cameron", 2009, 7.9);
        
        System.out.println("Movies (Forward Order):");
        movieList.displayMoviesForward();
        
        System.out.println("Movies (Reverse Order):");
        movieList.displayMoviesReverse();
        
        System.out.println("Updating Titanic's rating...");
        movieList.updateMovieRating("Titanic", 8.0);
        movieList.displayMoviesForward();
        
        System.out.println("Searching for movies directed by James Cameron...");
        movieList.searchMovie("James Cameron", -1);
        
        System.out.println("Removing Inception...");
        movieList.removeMovieByTitle("Inception");
        movieList.displayMoviesForward();
    }
}
