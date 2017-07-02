
public class LinearQueue<E> implements Queue<E> {

	private Object[] data;
	private int elementCount;
	private int incrementSeed;
	private int front;
	private int tail;

	public LinearQueue(int capacity, int incrementSeed) {
		data = new Object[capacity];
		this.incrementSeed = incrementSeed;
		elementCount = 0;
		front = tail = -1;
	}

	public LinearQueue(int capacity) {
		this(capacity, 20);
	}

	public LinearQueue() {
		this(10);
	}

	@Override
	public E enQueue(E item) {
		if (elementCount == data.length)
			grow();
		data[++tail] = item;
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		if (isEmpty())
			return null;
		int index = front + 1;
		return (E) data[index];
	}

	@Override
	public E deQueue() {
		E obj = peek();
		if (obj != null)
			front++;
		return obj;
	}

	@Override
	public boolean isEmpty() {
		return front == tail;
	}

	@Override
	public int size() {
		return tail - front;
	}

	private void grow() {
		int newCapacity = data.length + incrementSeed;

		Object[] oldData = new Object[data.length];
		for (int i = 0; i < oldData.length; i++)
			oldData[i] = data[i];

		data = new Object[newCapacity];
		for (int i = 0; i < newCapacity; i++)
			data[i] = oldData[i];
	}

}
