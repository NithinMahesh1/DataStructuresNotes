package simulator;

import static org.junit.Assert.*;

import org.junit.Test;


public class BusTest {

	@Test
	public void testConstructor() {
		Bus b = new Bus(1, new RoadMap(0, 0), 2, 3);
		assertEquals(1, b.number);
		assertEquals(2, b.getX());
		assertEquals(3, b.getY());
	}

	@Test
	public void testMoveNorthFromStop() {
		Bus b = new Bus(0, RoadMap.fromString("X.X X.X X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
	}
	
	@Test
	public void testMoveNorthTwice() {
		Bus b = new Bus(0, RoadMap.fromString("X.X X.X X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());		
	}

	@Test
	public void testMoveNorthThenEastOneWay() {
		Bus b = new Bus(0, RoadMap.fromString("XXX X.. X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());		
	}
	
	@Test
	public void testMoveSouthThenWestAtTee() {
		Bus b = new Bus(0, RoadMap.fromString("X.X ... XXX"), 1, 0);
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(0, b.getX());
		assertEquals(1, b.getY());		
	}
}
