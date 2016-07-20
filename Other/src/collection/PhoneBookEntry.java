package collection;

public class PhoneBookEntry {
	public String name;

	public PhoneBookEntry(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
