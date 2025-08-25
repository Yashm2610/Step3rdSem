package Week3.PracticeProblems;

import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;

    public Book(String title, String author, String isbn, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void displayBook() {
        System.out.println(title + " by " + author + " | ISBN: " + isbn + " | Price: " + price + " | Qty: " + quantity);
    }
}

class Library {
    private String libraryName;
    private ArrayList<Book> books;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) || book.getAuthor().equalsIgnoreCase(keyword)) {
                book.displayBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void displayInventory() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                book.displayBook();
            }
        }
    }

    public double calculateTotalValue() {
        double total = 0;
        for (Book book : books) {
            total += book.getPrice() * book.getQuantity();
        }
        return total;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library("City Library");

        while (true) {
            System.out.println("\n1. Add Book\n2. Search Book\n3. Display All Books\n4. Calculate Total Value\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    library.addBook(new Book(title, author, isbn, price, qty));
                    break;

                case 2:
                    System.out.print("Enter title or author to search: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;

                case 3:
                    library.displayInventory();
                    break;

                case 4:
                    System.out.println("Total value of books: " + library.calculateTotalValue());
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
