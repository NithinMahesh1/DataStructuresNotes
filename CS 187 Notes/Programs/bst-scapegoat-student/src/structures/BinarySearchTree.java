package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.corba.se.impl.orbutil.graph.Node;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	private int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		if(t == null) {
			throw new NullPointerException();
		}
		if(getHelper(t, root) == t) {
			return true;
		}
		//call get method
		return false;
	}

	public boolean remove(T t) {
		if(t == null) {
			throw new NullPointerException();
		}
 		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		if (node == null)
			return null;
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}
	
	private T getLowestValue(BSTNode<T> node) {
		if (node == null)
			return null;
		if(node.getLeft() == null) {
			return node.getData();
		} else {
			return getLowestValue(node.getLeft());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		if (node == null)
			throw new NullPointerException();
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		// TODO
		if(t == null) {
			throw new NullPointerException();
		}
		return getHelper(t, root);
	}
	
	public T getHelper(T t, BSTNode<T> node) {
		if(node == null) {
			return null;
		}
		if(node.getData().equals(t)) {
			return node.getData();
		}
		if(node.getData().compareTo(t) > 0) {
			return getHelper(t, node.getLeft());
		} else {
			return getHelper(t, node.getRight());
		}
	}

	public void add(T t) {
		if(t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(t, root);
	}

	private BSTNode<T> addToSubtree(T t, BSTNode<T> node) {
		if (node == null) {
			return new BSTNode<T>(t, null, null);
		}
		if (t.compareTo(node.getData()) <= 0) {
			node.setLeft(addToSubtree(t, node.getLeft()));
		} else {
			node.setRight(addToSubtree(t, node.getRight()));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		// TODO
		return getLowestValue(root);
		//return null;
	}

	@Override
	public T getMaximum() {
		// TODO
		return getHighestValue(root);
		//return null;
	}

	@Override
	public int height() {
		// TODO
		return heightHelper(this.getRoot());
	}
	
	public int heightHelper(BSTNode<T> node) {
		if(node == null) {
			return -1;
		} 
		return (1 + Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight())));
	}
	
	@Override
	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if(node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> postorderIterator() {
		// TODO		
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if(node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		if(other == null) {
			throw new NullPointerException();
		}
		if(other.size() != this.size()) {
			return false;
		}
		return equalsHelper(other.getRoot(), root);
		//return false;
	}
	
	public boolean equalsHelper(BSTNode<T> other, BSTNode<T> compare) {		
		if(compare != null && !compare.getData().equals(other.getData())) {
			return false;
		}
		if(other != null){	
		equalsHelper(other.getLeft(), compare.getLeft());
		equalsHelper(other.getRight(), compare.getRight());
		}
		return true;
	}
	
	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		if(other == null) {
			throw new NullPointerException();
		}
		if(other.size() != this.size()) {
			return false;
		}
		return sameValuesHelper(other);
	}
	
	public boolean sameValuesHelper(BSTInterface<T> other) {
		Iterator<T> compare = other.inorderIterator();
		if(compare.hasNext()) {
			if(!this.contains(compare.next())){
				return false;
			}
		}	
		return true;
	}

	@Override
	public boolean isBalanced() {
		// TODO
		if(root == null) {
			return true;
		}
		if(Math.pow(2, height()) <= size() && Math.pow(2, height()+1) > size()) {
			return true;
		}
		return false;
	}

	@Override
	public void balance() {
		// TODO		
		if(isEmpty()) {
			return;
		}
		Iterator<T> iter = inorderIterator();
		T[] array = (T[]) new Comparable[size()];
		BinarySearchTree<T> tree = new BinarySearchTree<T>();
		int count = 0;
		while(iter.hasNext()) {
			T store = iter.next();
			array[count] = store;
			count++;
		}
		balanceHelper(size()-1, 0, array, tree);
		this.root = tree.root;
	}
	
	public void balanceHelper(int upper, int lower, T[] array, BinarySearchTree<T> tree) {
		if(lower >= upper) {
			return;
		}
		int middle = (upper+lower)/2;
		tree.add(array[middle]);
		balanceHelper(upper, middle+1, array, tree);
		balanceHelper(middle-1, lower, array, tree);
	}

	@Override
	public BSTNode<T> getRoot() {
		// DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// DO NOT MODIFY
		// see project description for explanation

		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}
}