package list;

public class Node<E> {
	
	private E value;
	private Node<E> next;
	
	public Node(E value){
		// TODO
		this.value = value;
	}
	
	public void setValue(E value){
		// TODO
		this.value = value;
	}
	
	public E getValue(){
		// TODO
		return value;
	}
	
	public void setNext(Node<E> next){
		// TODO
		this.next = next;
	}
	
	public Node<E> getNext(){
		// TODO
		return this.next;
	}

}
