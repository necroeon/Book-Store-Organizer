// -----------------------------------------------------
// Assignment 00
// Written by: Md Khairul Enam Adib
// SID: 40211709
// -----------------------------------------------------

package book;

public class Book {
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private static int bookCount = 0;
	
	public Book () { //default constructor
		this.title= "";
		this.author= "";
		this.ISBN=0;
		this.price=0;
		bookCount++;
	}
	
	public Book(String title, String author, long ISBN, double price) { //parameterized constructor
		this.title = title;
		this.author=author;
		this.ISBN=ISBN;
		this.price=price;
	}
	 
	public Book(Book b) { //copy constructor
		this.title=b.title;
		this.author=b.author;
		this.ISBN=b.ISBN;
		this.price=b.price;
	}
	
	
	//accessors and mutators
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author=author;
	}
	
	public long getISBN() {
		return this.ISBN;
	}
	
	public void setISBN(long ISBN) {
		this.ISBN=ISBN;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price=price;
	}
	
	public static int findNumberOfCreatedBooks() {
		return bookCount;
	}
	
	public boolean equals(Object obj) { //equals method
		 if (this == obj)
		        return true;
		    if (obj == null || !(obj instanceof Book))
		        return false;
		    
		 Book b = (Book) obj;
		 
        if(ISBN != b.ISBN) 
       	 return false;
        if(price != b.price) 
          	 return false;
        
        return true;
	}
	
    public void displayBookInfo(int index) { //display method for option 2
        System.out.println("Book #" + index);
        System.out.println("Author: " + author);
        System.out.println("Title: " + title);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Price: $" + price);
    }
    
	
	public String toString() { //toString or output method
		return "Book Title: " + this.title + "\nBook Author: " + this.author + "\nBook ISBN: " + this.ISBN + "\nBook Price: " + this.price ;
	}

}
