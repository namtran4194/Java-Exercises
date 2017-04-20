package composition;

public class TestAuthorBook {

	public static void main(String[] args) {
		Author namTX = new Author("Tran Nam", "nam3vj@gmail.com", 'm');
		System.out.println(namTX);
		Book game = new Book("LOL", namTX, 250000, 2);
		System.out.println(game);
	}
}
