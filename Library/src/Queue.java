
interface Queue<E> {
	
	public E enQueue(E item);
	
	public E peek();
	
	public E deQueue();
	
	public boolean isEmpty();
	
	public int size();
}
