
public class Stack<E> {

	private Object[] data;
	private int elementCount;
	private int incrementSeed;

	public Stack(int capacity, int incrementSeed) {
		data = new Object[capacity];
		this.incrementSeed = incrementSeed;
		elementCount = 0;
	}

	public Stack(int capacity) {
		this(capacity, 20);
	}

	public Stack() {
		this(10);
	}

	public E push(E item) {
		if (size() == data.length)
			grow();
		data[elementCount++] = item;
		return item;
	}

	@SuppressWarnings("unchecked")
	public E peek() {
		if (isEmpty())
			return null;
		int index = elementCount - 1;
		return (E) data[index];
	}

	public E pop() {
		E obj = peek();
		if (obj != null)
			elementCount--;
		return obj;
	}

	public boolean isEmpty() {
		return elementCount == 0;
	}

	public int size() {
		return elementCount;
	}

	private void grow() {
		int newCapacity = data.length + incrementSeed;
		Object[] oldData = data;

		data = new Object[newCapacity];
		for (int i = 0; i < oldData.length; i++)
			data[i] = oldData[i];
	}
}
