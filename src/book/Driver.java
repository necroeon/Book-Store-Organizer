// -----------------------------------------------------
// Assignment 00
// Written by: Md Khairul Enam Adib
// SID: 40211709
// -----------------------------------------------------

package book;
import java.util.Scanner;

public class Driver{
	private static final String PASSWORD = "249";
    private static final int MAX_ATTEMPTS = 3;
    private static final int MAX_ROUNDS = 4;

    // Array to hold Book objects, and a counter to track how many books are added
    private static final int MAX_BOOKS = 10;
    private static Book[] inventory = new Book[MAX_BOOKS];
    private static int currentBookCount = 0;
    
public static void main (String[] args) {
	Scanner scanner = new Scanner(System.in);
	
	System.out.println("Welcome to BookStore Organizer");
	 
	 System.out.println("How many books can your store contain?");
	int maxBooks = scanner.nextInt();
	
	maxBooks = MAX_BOOKS;
	 
	 Book[] inventory = new Book [maxBooks];
	 menu();

	 }

public static void menu() { 
	Scanner scanner = new Scanner(System.in);
	
	 System.out.println(
			 "What do you want to do?\n" + 
			 "\t1. Enter new books(password required)\n" +
			 "\t2. Change information of a book(password required)\n" +
			 "\t3. Display all books by a specific author\n" +
			 "\t4. Display all books under a certain a price\n" +
			 "\t5. Quit\n" +
	 		"Please enter your choice > \n");
	 		
	 int option = scanner.nextInt();
	 
	 switch(option) {
	 	case 1:
			checkPassword1();
	 		break;
	 	case 2:
	 		checkPassword2();
	 		break;
	 	case 3:
	 		System.out.println("Name the author: ");
	 		String authorName = scanner.nextLine();
	 		findBooksBy(authorName);
	 		break;
	 	case 4:
	 		System.out.println("Enter the price: ");
	 		double cheapPrice = scanner.nextDouble();
	 		findCheaperThan(cheapPrice);
	 		break;
	 	case 5:
	 		System.out.println("Thank you for using BookStore Organizer!");
	 		break;
	 	default:
	 		System.out.println("Invalid Input");
	 		break;
	 }
}

	public static void checkPassword1() {
	    Scanner scanner = new Scanner(System.in);
	    int failedAttempts = 0; // Track total failed attempts
	
	    // Outer loop for rounds (up to 4 rounds)
	    for (int round = 1; round <= MAX_ROUNDS; round++) {
	        // Inner loop for attempts within each round (up to 3 attempts)
	        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
	            System.out.print("Please enter your password: ");
	            String inputPassword = scanner.nextLine();
	
	            if (inputPassword.equals(PASSWORD)) {
	                System.out.println("Access granted!");
	                addBooks(scanner);
	                return;  // Exit the method when password is correct
	            } else {
	                System.out.println("Incorrect password. Attempt " + attempt + " of " + MAX_ATTEMPTS);
	                failedAttempts++;
	
	                // Check if total failed attempts have reached the maximum (12 attempts)
	                if (failedAttempts == MAX_ROUNDS * MAX_ATTEMPTS) {
	                    System.out.println("Program detected suspicious activities and will terminate immediately!");
	                    System.exit(0);  // Terminate the program
	                }
	            }
	        }
	        // After 3 failed attempts, redisplay the main menu
	        System.out.println("Returning to main menu after 3 failed attempts.");
	        menu();
	    }
	    scanner.close();  // Close the scanner when done
	}
	
    public static void addBooks(Scanner scanner) {
        System.out.print("How many books would you like to add? ");
        int numberOfBooks = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Check if there is enough space in the array to add the books
        if (currentBookCount + numberOfBooks <= MAX_BOOKS) {
            // Space is available, proceed to add books
            for (int i = 0; i < numberOfBooks; i++) {
                System.out.println("Enter details for book " + (i + 1));
                
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                
                System.out.print("Enter book author: ");
                String author = scanner.nextLine();
                
                System.out.print("Enter book ISBN: ");
                long ISBN = scanner.nextLong();
                
                System.out.print("Enter book price: ");
                double price = scanner.nextDouble();

                // Add the new Book object to the array
                inventory[currentBookCount] = new Book(title, author, ISBN, price);
                currentBookCount++;
            }
            System.out.println(numberOfBooks + " books added successfully!");
            menu();
        } else {
            // Not enough space to add all books
            int remainingSpace = MAX_BOOKS - currentBookCount;
            System.out.println("Not enough space in the bookstore. You can only add " + remainingSpace + " more books.");
        }
    }
    
    private static boolean checkPassword2() {
    	Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Please enter your password: ");
            String inputPassword = scanner.nextLine();

            if (inputPassword.equals(PASSWORD)) {
                System.out.println("Access granted!");
                updateBookInformation();
                return true;  // Password correct
            } else {
                attempts++;
                System.out.println("Incorrect password. Attempt " + attempts + " of " + MAX_ATTEMPTS);
            }
        }

        // After 3 failed attempts, redisplay the main menu
        System.out.println("Returning to main menu after 3 failed attempts.");
        return false;
    }
    

    private static void updateBookInformation() {
    	Scanner scanner = new Scanner (System.in);
        while (true) {
            System.out.print("Enter the book number (index) you want to update (0-9): ");
            int index = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Check if the index is valid
            if (index < 0 || index >= inventory.length || inventory[index] == null) {
                System.out.println("No book exists at that index.");
                System.out.print("Do you want to re-enter another index (y/n)? ");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("y")) {
                    System.out.println("Returning to main menu.");
                    break;
                }
            } else {
                // Valid index, display book info and allow updates
                inventory[index].displayBookInfo(index);
                boolean continueUpdating = true;

                while (continueUpdating) {
                    System.out.println("What information would you like to change?");
                    System.out.println("1. Author");
                    System.out.println("2. Title");
                    System.out.println("3. ISBN");
                    System.out.println("4. Price");
                    System.out.println("5. Quit");
                    System.out.print("Enter your choice > ");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    switch (updateChoice) {
                        case 1:
                            System.out.print("Enter new author: ");
                            String newAuthor = scanner.nextLine();
                            inventory[index].setAuthor(newAuthor);
                            break;
                        case 2:
                            System.out.print("Enter new title: ");
                            String newTitle = scanner.nextLine();
                            inventory[index].setTitle(newTitle);
                            break;
                        case 3:
                            System.out.print("Enter new ISBN: ");
                            long newISBN = scanner.nextLong();
                            inventory[index].setISBN(newISBN);
                            break;
                        case 4:
                            System.out.print("Enter new price: ");
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();  // Consume newline
                            inventory[index].setPrice(newPrice);
                            break;
                        case 5:
                            continueUpdating = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                            continue;
                    }

                    // Display updated book info after each change
                    inventory[index].displayBookInfo(index);
                }
                break;// Exit the book update loop and return to the main menu
            }
            
        }
        menu();
    }
    
    private static void findBooksBy(String authorName) { //option 3 method
        boolean bookFound = false;

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getAuthor().equalsIgnoreCase(authorName)) {
                inventory[i].displayBookInfo(i);
                bookFound = true;
            }
        }

        if (!bookFound) {
            System.out.println("No books found by the author: " + authorName);
        }
    }
    
    private static void findCheaperThan(double price) { //option 4 method
        boolean bookFound = false;

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getPrice() < price) {
                inventory[i].displayBookInfo(i);
                bookFound = true;
            }
        }

        if (!bookFound) {
            System.out.println("No books found cheaper than $" + price);
        }
    }
    
    
}




	