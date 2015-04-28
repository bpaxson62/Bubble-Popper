package edu.fgcu;

import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreControllerTest {
	private static ScoreBoardController scoreBoardController;
	private static Level level;
	private static GameController gameController;
	BorderPane root;
	
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
	root = new BorderPane();
	scoreBoardController = new ScoreBoardController(root);
	//GameControllerTest.myLevel = new Level(0);
	
	gameController = Main.getGameController();
	}
	
	//Some test classes for GUI will be auto-created using WindowTester
	
	@Test
	public void testScoreboardCreate() {
		//test for if scoreboard is created when button to view is clicked
		assertNotNull(root);
	}
	
	@Test
	public void testAddLastScore() {
		scoreBoardController.addScoreToList(10, 1);
		assertEquals("Score should equal 10, difficulty 1", testScores, scoreBoardController.getAllScores() );
		
	}
	
	@Test
	public void testUpdateHighScore() {
		
		ScoreBoardController.setHighScore(10);
		int newScoreLower = 5;
		int newScoreHigher = 20;
		//scoreBoardController.comapreScore(newScoreLower);
		ScoreBoardController.setHighScore(newScoreLower);
		assertEquals("Should be 10", 10, scoreBoardController.getHighScore() );
		
		ScoreBoardController.setHighScore(newScoreHigher);
		assertEquals("Should be 20",20, scoreBoardController.getHighScore() );
		
	}
	 
}
