package java.edu.fgcu;

import javafx.collections.ObservableList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreControllerTest {
	private static ScoreBoardController scoreBoardController;
	
	public class TestObject{
		private int score;
		private String difficulty;
		TestObject(int score, String difficulty){
			this.score=score;
			this.difficulty=difficulty;
		}
	}
	
	protected ObservableList<TestObject> testScores;
	public void addTestScores(){
		testScores.add(new TestObject(10, "Easy"));
	}
	@Before
	public void setup() {
	scoreBoardController = new ScoreBoardController(null);
	
	}
	
	//Some test classes for GUI will be auto-created using WindowTester
	
	@Test
	public void testScoreboardCreate() {
		//test for if scoreboard is created when button to view is clicked
		throw new RuntimeException();
	}
	
	@Test
	public void testAddLastScore() {
		scoreBoardController.addScoreToList(10, 1);
		assertEquals("Score should equal 10, difficulty 1", testScores, scoreBoardController.getAllScores() );
		
	}
	
	@Test
	public void testUpdateHighScore() {
		
		scoreBoardController.setHighScore(10);
		int newScoreLower = 5;
		int newScoreHigher = 20;
		scoreBoardController.comapreScore(newScoreLower);
		assertEquals("Should be 10", 10, scoreBoardController.getHighScore() );
		
		scoreBoardController.comapreScore(newScoreHigher);
		assertEquals("Should be 20",20, scoreBoardController.getHighScore() );
		
	}
	 
}
