package exercisesonComposition;

public class Book {
	private String name;
	private Author author;
	private double price;
	private int qty;

	public Book(String name, Author author, double price) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.qty = 0;
	}

	public Book(String name, Author author, double price, int qty) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Book[name = " + name + ",Author[name = " + author.getName() + ",email = " + author.getEmail()
				+ ",gender = " + author.getGender() + "]" + ",price = " + price + ",qty = " + qty + "]";
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public Author getAuthor() {
		return author;
	}

}
