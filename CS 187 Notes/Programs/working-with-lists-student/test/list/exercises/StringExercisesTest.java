package list.exercises;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class StringExercisesTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testCountCharactersEmpty() {
		assertEquals(0, StringExercises.countCharacters(new ArrayList<String>()));
	}
	
	@Test
	public void testCountCharactersEmptyString() {
		assertEquals(0, StringExercises.countCharacters(Arrays.asList("")));
	}

	@Test
	public void testCountCharactersEmptyStrings() {
		assertEquals(0, StringExercises.countCharacters(Arrays.asList("", "", "")));
	}
	
	@Test
	public void testCountCharactersOneString() {
		assertEquals(9, StringExercises.countCharacters(Arrays.asList("asdf jkl;")));
	}

	@Test
	public void testCountCharactersThreeStrings() {
		assertEquals(15, StringExercises.countCharacters(Arrays.asList("asdf jkl;", "", "123456")));
	}

	@Test
	public void testSplitZero() {
		assertEquals(Arrays.asList(""), 
				StringExercises.split(""));
	}

	@Test
	public void testSplitOne() {
		assertEquals(Arrays.asList("banana"),
				StringExercises.split("banana"));
	}

	@Test
	public void testSplitThree() {
		assertEquals(Arrays.asList("Larry", "Moe", "Curly"), 
				StringExercises.split("Larry   Moe\nCurly "));
	}

	@Test
	public void testUppercasedEmptyList() {
		assertEquals(Arrays.asList(), StringExercises.uppercased(Arrays.asList()));
	}
	
	@Test
	public void testUppercasedEmpty() {
		assertEquals(Arrays.asList(""), StringExercises.uppercased(Arrays.asList("")));
	}
	
	@Test
	public void testUppercasedList() {
		assertEquals(Arrays.asList("ASDF", "JKL;", "!@#$"), 
				StringExercises.uppercased(Arrays.asList("asdf", "jkl;", "!@#$")));
	}

	@Test
	public void testUppercasedParameterUnchanged() {
		List<String> parameter = Arrays.asList("asdf", "jkl;", "!@#$");
		StringExercises.uppercased(parameter);
		assertEquals(Arrays.asList("asdf", "jkl;", "!@#$"), 
				parameter);
	}
	
	@Test
	public void testAllCapitalizedWordsEmptyList() {
		assertFalse(StringExercises.allCapitalizedWords(Arrays.asList()));
	}

	@Test
	public void testAllCapitalizedWordsEmptyString() {
		assertFalse(StringExercises.allCapitalizedWords(Arrays.asList("")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsFalse1() {
		assertFalse(StringExercises.allCapitalizedWords(Arrays.asList("Asdf", "Jkl;", "qwer")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsFalse2() {
		assertFalse(StringExercises.allCapitalizedWords(Arrays.asList("Asdf", "Jkl;", "!@#$")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsFalse3() {
		assertFalse(StringExercises.allCapitalizedWords(Arrays.asList("Asdf", "", "Jkl;")));
	}

	@Test
	public void testAllCapitalizedWordsOneStringTrue() {
		assertTrue(StringExercises.allCapitalizedWords(Arrays.asList("Asdf")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsTrue() {
		assertTrue(StringExercises.allCapitalizedWords(Arrays.asList("Asdf", "Jkl;", "Qwer")));
	}

	@Test
	public void testFilterContainingEmptyList() {
		assertEquals(Arrays.asList(), StringExercises.filterContaining(Arrays.asList(), 'a'));
	}

	@Test
	public void testFilterContainingEmptyStrings() {
		assertEquals(Arrays.asList(), StringExercises.filterContaining(Arrays.asList("", ""), 'b'));
	}
	
	@Test
	public void testFilterContainingEmptyAndMatchingStrings() {
		assertEquals(Arrays.asList("Marc"), 
				StringExercises.filterContaining(Arrays.asList("Marc", "", "banana"), 'c'));
	}

	@Test
	public void testFilterContainingMultipleMatchingStrings() {
		assertEquals(Arrays.asList("David", "Ted", "Bundy"), 
				StringExercises.filterContaining(Arrays.asList("Marc", "David", "Ted", "Bundy"), 'd'));
	}
	
	@Test
	public void testFilterContainingParameterUnchanged() {
		List<String> parameter = Arrays.asList("Marc", "David", "Ted", "Bundy"); 
		StringExercises.filterContaining(parameter, 'e');
		assertEquals(Arrays.asList("Marc", "David", "Ted", "Bundy"), parameter);
	}
	
	@Test
	public void testInsertInOrderEmpty() {		
		List<String> l = new ArrayList<String>();;
		StringExercises.insertInOrder("Josh", l);
		assertEquals(Arrays.asList("Josh"), l);
	}	

	@Test
	public void testInsertInOrderFirst() {		
		List<String> l = new ArrayList<String>();
		l.add("Marc");
		StringExercises.insertInOrder("Josh", l);
		assertEquals(Arrays.asList("Josh", "Marc"), l);
	}	

	@Test
	public void testInsertInOrderMiddle() {		
		List<String> l = new ArrayList<String>();
		l.add("Josh");
		l.add("Marc");
		StringExercises.insertInOrder("Lisa", l);
		assertEquals(Arrays.asList("Josh", "Lisa", "Marc"), l);
	}
	
	@Test
	public void testInsertInOrderEnd() {		
		List<String> l = new ArrayList<String>();
		l.add("Josh");
		l.add("Lisa");
		StringExercises.insertInOrder("Marc", l);
		assertEquals(Arrays.asList("Josh", "Lisa", "Marc"), l);
	}
	
	
}
