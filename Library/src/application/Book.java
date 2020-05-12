package application;

public class Book {
	private String isbn;
	private String title;
	private String genre;
	private String author;
	private String pubcode;
	private String status;
	
	public Book(String isbn, String title, String genre, String author, String pubcode, String status) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.pubcode = pubcode;
		this.status = status;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPubcode() {
		return pubcode;
	}
	public void setPubcode(String pubcode) {
		this.pubcode = pubcode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
