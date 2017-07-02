
public class CircularQueue<E> implements Queue<E> {

	private Object[] data;
	private int front;
	private int tail;

	public CircularQueue(int capacity) {
		data = new Object[capacity];
		front = tail = 0;
	}

	@Override
	public E enQueue(E item) {
		if (size() != data.length)
			data[++tail % data.length] = item;
		else
			return null;
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		if (isEmpty())
			return null;
		int index = front + 1;
		return (E) data[index % data.length];
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
		return size() == 0;
	}

	@Override
	public int size() {
		return tail - front;
	}

}
