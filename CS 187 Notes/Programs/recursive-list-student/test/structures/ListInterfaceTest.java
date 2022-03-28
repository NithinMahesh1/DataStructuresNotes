package structures;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListInterfaceTest {
	//Make instance variables to use for several tests
	
	RecursiveList<String> rl;
	RecursiveList<String> r2;
	
	@Before
	public void before() {
		rl = new RecursiveList<String>();
		r2 = new RecursiveList<String>();
		
		//Do stuff before each of the tests (such as set up a deque with some data)
	}
	
	@After
	public void after() {
		//Clean up after each test (mainly useful for file i/o)
	}
	
	@Test
	public void testsizeinserfirstremovelast(){
		assertEquals("test size empty", 0, rl.size());
		assertEquals("test is empty (true)", true, rl.isEmpty());
		rl.insertFirst("first");
		assertEquals("test size after insertFirst 1", 1, rl.size());
		assertEquals("test is empty (false)", false, rl.isEmpty());
		rl.insertFirst("second");
		assertEquals("test size after insertFirst 1", 2, rl.size());
		rl.insertFirst("third");
		assertEquals("test size after insertFirst 1", 3, rl.size());
		assertEquals("test remove last 1", "first", rl.removeLast());
		assertEquals("test remove last 2", "second", rl.removeLast());
		assertEquals("test remove last 3", "third", rl.removeLast());
		assertEquals("test size after all removed", 0, rl.size());
	}
	
	@Test
	public void testremovefirst(){
		
		r2.insertFirst("first");
		r2.insertFirst("second");
		r2.insertFirst("third");
		assertEquals("test remove first 1", "third", r2.removeFirst());
		assertEquals("test remove first 2", "second", r2.removeFirst());
		assertEquals("test remove first 3", "first", r2.removeFirst());
		assertEquals("test size after all removed", 0, r2.size());
	}
	
	@Test
	public void testinsertlast(){
		rl.insertLast("first");
		rl.insertLast("second");
		rl.insertLast("third");
		assertEquals("test remove first 1", "first", rl.removeFirst());
		assertEquals("test remove first 2", "second", rl.removeFirst());
		assertEquals("test remove first 3", "third", rl.removeFirst());
		assertEquals("test size after all removed", 0, rl.size());
	}
	
	@Test
	public void testinsertat(){
		rl.insertAt(0, "first");
		rl.insertAt(0, "second");
		rl.insertAt(1, "third");
		rl.insertAt(3, "fourth");
		rl.insertAt(0,  "fifth");
		
		assertEquals("test remove first 1", "fifth", rl.removeFirst());
		assertEquals("test remove first 1", "second", rl.removeFirst());
		assertEquals("test remove first 2", "third", rl.removeFirst());
		assertEquals("test remove first 3", "first", rl.removeFirst());
		assertEquals("test remove first 3", "fourth", rl.removeFirst());
		assertEquals("test size after all removed", 0, rl.size());
	}
	
	@Test
	public void testremoveat(){
		rl.insertAt(0, "first");
		rl.insertAt(1, "second");
		rl.insertAt(2, "third");
		rl.insertAt(3, "fourth");
		rl.insertAt(4, "fifth");
		
		//assertEquals("test remove at 0", "first", rl.removeAt(0));
		rl.removeAt(0);
		assertEquals("test this index 0", "second", rl.get(0));
		assertEquals("test remove at 2", "fourth", rl.removeAt(2));
		assertEquals("test remove at 2 (end)", "fifth", rl.removeAt(2));
		assertEquals("test remove at 1", "third", rl.removeAt(1));
		assertEquals("test remove at 0", "second", rl.removeAt(0));
		assertEquals("test size after all removed", 0, rl.size());
	}
	
	@Test
	public void testatfirstatlast(){
		rl.insertAt(0, "first");
		rl.insertAt(1, "second");
		rl.insertAt(2, "third");
		rl.insertAt(3, "fourth");
		rl.insertAt(4, "fifth");
		
		assertEquals("test getfirst", "first", rl.getFirst());
		assertEquals("test getlast", "fifth", rl.getLast());
	}
	
	@Test
	public void testget(){
		rl.insertAt(0, "first");
		rl.insertAt(1, "second");
		rl.insertAt(2, "third");
		rl.insertAt(3, "fourth");
		rl.insertAt(4, "fifth");
		
		assertEquals("test getfirst", "first", rl.get(0));
		assertEquals("test getfirst", "third", rl.get(2));
		assertEquals("test getfirst", "second", rl.get(1));
		assertEquals("test getfirst", "fourth", rl.get(3));
		assertEquals("test getfirst", "fifth", rl.get(4));
	}
	
	@Test
	public void testremove(){
		rl.insertAt(0, "first");
		rl.insertAt(1, "second");
		rl.insertAt(2, "third");
		rl.insertAt(3, "fourth");
		rl.insertAt(4, "fifth");
		rl.insertAt(5, "first");
		rl.insertAt(6, "sixth");
		
		assertTrue("remove first first", rl.remove("first"));
		assertEquals("remove fifth", rl.remove("fifth"), true);
		assertEquals("remove sixth", rl.remove("sixth"), true);
		assertEquals("remove non existant", rl.remove("not there"), false);

		assertEquals("check size after remove", 4, rl.size());
		assertEquals("remove first",  "second", rl.removeFirst());
		assertEquals("remove first", "third", rl.removeFirst());
		assertEquals("remove first", "fourth", rl.removeFirst());
		assertEquals("remove first", "first", rl.removeFirst());
		
		assertEquals("check size after remove", 0, rl.size());
	}
	
	@Test
	public void testindexof(){
		rl.insertAt(0, "first");
		rl.insertAt(1, "second");
		rl.insertAt(2, "third");
		rl.insertAt(3, "fourth");
		rl.insertAt(4, "fifth");
		rl.insertAt(5, "first");
		rl.insertAt(6, "sixth");
		
		assertEquals("index of first", 0, rl.indexOf("first"));
		assertEquals("index of second", 1, rl.indexOf("second"));
		assertEquals("index of fourth", 3, rl.indexOf("fourth"));
		assertEquals("index of sixth", 6, rl.indexOf("sixth"));
	}
	
	@Test
	public void testiterator(){
		rl.insertAt(0, "first");
		rl.insertAt(1, "second");
		rl.insertAt(2, "third");
		rl.insertAt(3, "fourth");
		rl.insertAt(4, "fifth");
		rl.insertAt(5, "sixth");
		
		Iterator<String> i = rl.iterator();
		assertEquals("iterator hasNext true", true, i.hasNext());
		assertEquals("iterator first", "first", i.next());
		assertEquals("iterator second", "second", i.next());
		assertEquals("iterator third", "third", i.next());
		assertEquals("iterator hasNext true", true, i.hasNext());
		assertEquals("iterator fourth", "fourth", i.next());
		assertEquals("iterator fifth", "fifth", i.next());
		assertEquals("iterator sixth", "sixth", i.next());
		assertEquals("iterator hasNext false", false, i.hasNext());

	}
	// test exceptions
		
	@Test(timeout = 500, expected = NoSuchElementException.class)
	public void testiteratorexception() throws NoSuchElementException {
		Iterator<String> i = rl.iterator();
		i.next();
	}
	
	
	@Test(timeout = 500, expected = NullPointerException.class)
	public void testinsertFirstException() throws NullPointerException {
		rl.insertFirst(null);
	}
	
	@Test(timeout = 500, expected = NullPointerException.class)
	public void testinsertLastException() throws NullPointerException {
		rl.insertLast(null);
	}
	
	@Test(timeout = 500, expected = NullPointerException.class)
	public void testinsertAtExceptionNull() throws NullPointerException {
		rl.insertAt(0, null);
	}
	
	@Test(timeout = 500, expected = IndexOutOfBoundsException.class)
	public void testinsertAtExceptionIndextoosmall() throws IndexOutOfBoundsException {
		rl.insertAt(-1, null);
	}
	
	@Test(timeout = 500, expected = IndexOutOfBoundsException.class)
	public void testinsertAtExceptionIndextoolarge() throws IndexOutOfBoundsException {
		rl.insertAt(1, null);
	}
	
	@Test(timeout = 500, expected = IllegalStateException.class)
	public void testremoveFirstempty() throws IllegalStateException {
		rl.removeFirst();
	}
	
	@Test(timeout = 500, expected = IllegalStateException.class)
	public void testremoveLastempty() throws IllegalStateException {
		rl.removeLast();
	}
	
	@Test(timeout = 500, expected = IndexOutOfBoundsException.class)
	public void testremoveAtExceptionIndextoosmall() throws IndexOutOfBoundsException {
		RecursiveList<String> rl = new RecursiveList<String>();
		rl.removeAt(-1);
	}
	
	@Test(timeout = 500, expected = IndexOutOfBoundsException.class)
	public void testremoveAtExceptionIndextoolarge() throws IndexOutOfBoundsException {
		RecursiveList<String> rl = new RecursiveList<String>();
		rl.removeAt(1);
	}
	
	@Test(timeout = 500, expected = IllegalStateException.class)
	public void testgetFirstempty() throws IllegalStateException {
		RecursiveList<String> rl = new RecursiveList<String>();
		rl.getFirst();
	}
	
	@Test(timeout = 500, expected = IllegalStateException.class)
	public void testgetLastempty() throws IllegalStateException {
		RecursiveList<String> rl = new RecursiveList<String>();
		rl.getLast();
	}
	
	@Test(timeout = 500, expected = NullPointerException.class)
	public void testremoveExceptionNull() throws NullPointerException {
		RecursiveList<String> rl = new RecursiveList<String>();
		rl.remove(null);
	}
	
	@Test(timeout = 500, expected = NullPointerException.class)
	public void testindexofExceptionNull() throws NullPointerException {
		RecursiveList<String> rl = new RecursiveList<String>();
		rl.indexOf(null);
	}
	
}