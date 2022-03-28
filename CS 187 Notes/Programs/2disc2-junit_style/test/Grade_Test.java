import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class Grade_Test {

	private Grade grade;
	
	@Before
	public void before() {
		grade = new Grade(0);
	}
	
	@Test (timeout = 1000)
	public void test_getTotalScoreAndUpdateScore() {
		int[] subScores_to_add={30, 20, 50};
		assertEquals(100, grade.getTotalScoreAndUpdateScore(subScores_to_add));
		
		subScores_to_add=new int[]{30, 22};
		//FILL IN
		assertEquals(52, grade.getTotalScoreAndUpdateScore(subScores_to_add));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_getTotalScore_IllegalArgumentException(){
		//FILL IN
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

	@Test (timeout = 1000)
	public void test_getLetterGrade() {
		grade=new Grade(95);
		assertEquals("A",grade.getLetterGrade());
		
		//FILL IN
		
		
		
		
		
		
		
		
		
		
		
	}
	
	@Test (timeout = 1000)
	public void test_isValidScore() {
		//FILL IN

		
		
		
		
		
		
	}
	
	
}
