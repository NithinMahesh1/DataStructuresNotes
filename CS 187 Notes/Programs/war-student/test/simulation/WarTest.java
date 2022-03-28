package simulation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class WarTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testEmpty() {
		assertEquals(0, War.findWinner(new ArrayList<Integer>()));
	}
	
	@Test
	public void testOne() {
		List<Integer> deck = Arrays.asList(2);
		assertEquals(1, War.findWinner(deck));
	}

	@Test
	public void testTwoA() {
		List<Integer> deck = Arrays.asList(3, 2);
		assertEquals(1, War.findWinner(deck));
	}

	@Test
	public void testTwoB() {
		List<Integer> deck = Arrays.asList(2, 3);
		assertEquals(-1, War.findWinner(deck));
	}
}
