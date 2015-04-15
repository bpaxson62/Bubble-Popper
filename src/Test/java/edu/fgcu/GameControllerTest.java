package edu.fgcu;

import javafx.scene.layout.BorderPane;

import org.junit.Before;
import org.junit.Test;

import edu.fgcu.GameController;
import static org.junit.Assert.*;

public class GameControllerTest {
	private static GameController gameController;
	BorderPane root;

	@Before
	public void setup() {
		//put setup info here
		root = new BorderPane();
		root.setCenter(new Level(1));
		gameController = new GameController(root);
		gameController.gameStart();
	}
	
	//Some test classes for GUI will be auto-created using WindowTester
	
	@Test
	public void testMainPaneCreate() {
		//test for if main screen created
		assertNotNull(root);
	}
	
	@Test
	public void testEasyDifficultySettings() {
		//Test if setting changed correctly based on difficulty selected
		//Settings should match those that correspond to chosen difficulty
		gameController.setDifficulty(1);
		int difficulty = gameController.getDifficulty();
		assertEquals("Should equal 1",difficulty,1);
	}
	
	@Test
	public void testNormalDifficultySettings() {
		//Test if setting changed correctly based on difficulty selected
		//Settings should match those that correspond to chosen difficulty
		gameController.setDifficulty(2);
		int difficulty = gameController.getDifficulty();
		assertEquals("Should equal 2",difficulty,2);
	}
	
	@Test
	public void testHardDifficultySettings() {
		//Test if setting changed correctly based on difficulty selected
		//Settings should match those that correspond to chosen difficulty
		gameController.setDifficulty(3);
		int difficulty = gameController.getDifficulty();
		assertEquals("Should equal 3",difficulty,3);
		
	}
	
	@Test
	public void testLifeDecrease() {
		//test for life decreasing when bubbles poped
		//life should decrease only if bubbles pop due to hitting other bubbles
		//hitting the wall
		gameController.decreaseLifePoints();
		assertEquals("Life Points should equal 9", 9, gameController.getLifePoints());
	
	}
	
	@Test
	public void testIncreaseScore() {
		//give conditions for bubble poped, should increase score properly
		gameController.increaseScore();
		assertEquals("Score should equal 1",1,gameController.getScore());
	}
	
	@Test
	public void testLifePointstoZero() {
		//game should end
		for(int i =0;i<10; i++ ){
			gameController.decreaseLifePoints();
		}
		assertEquals("Life Points should equal 0", 0, gameController.getLifePoints());
		assertNull(root.getCenter());
	}
	
	@Test
	public void testUserEndsGame(){
		//test for when user ends game manually

		root.setCenter(new Level(1));
		gameController.endGame();
		assertNull(root.getCenter());
	}
}