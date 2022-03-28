package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;


import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 * 
 * @author liberato
 *
 */
public class LevelOrderIterator extends FileIterator<File> {	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	File start;
	Queue<File> allFiles;
	
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		// TODO 1
		if(!rootNode.exists()) {
			throw new FileNotFoundException();
		}
		start = rootNode;
		allFiles = new Queue<File>();
		allFiles.enqueue(start);
		if(start.isDirectory()) {
		}
	}
	
	@Override
	public boolean hasNext() {
		// TODO 2
		return (!allFiles.isEmpty()); 	
	}

	@Override
	public File next() throws NoSuchElementException {
		// TODO 3
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		if(allFiles.peek().isDirectory()) {
			File[] temp = allFiles.peek().listFiles();
			Arrays.sort(temp);
			for(int i=0; i<temp.length; i++) {
				allFiles.enqueue(temp[i]);	
			}
			return allFiles.dequeue();
		}
		return allFiles.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
