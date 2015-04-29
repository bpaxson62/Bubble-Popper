package edu.fgcu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameControllerTest extends Application{
	 private static GameController gameController;
	    Level testLevel;
	    BorderPane root;

	    @BeforeClass
	    public static void initJFX() {
	        Thread t = new Thread("Initialize the JavaFX") {
	            @Override
	            public void run() {
	                Application.launch(Main.class, new String[0]);
	            }
	        };
	        t.setDaemon(true);
	        t.start();
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {

	        //put setup info here
	        // This creates the basic window, commented out for the moment
	        //This works, must remove init(primaryStage) to make it wor
	         root = new BorderPane();
	        Group gameGroup = new Group();
	        root.setCenter(gameGroup);

	        gameController = new GameController(root);

	        ScoreBoardController scoreBoardController = new ScoreBoardController(root);
	        scoreBoardController.createToolBar(1);
	        primaryStage.setTitle("Bubble Popper");
	        primaryStage.setResizable(false);
	        primaryStage.setWidth(Configurations.MAIN_SCREEN_WIDTH + 2 * Configurations.WINDOW_BOARDER);
	        primaryStage.setHeight(Configurations.MAIN_SCREEN_HEIGHT + 2 * Configurations.WINDOW_BOARDER);
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        //scene.setFill(Color.BLACK);
	        //Button button = new Button("All Scores");
	        testLevel = new Level(1);

	    }
//	private static GameController gameController;
//	private static ScoreBoardController scoreBoardController;
//	public static Level myLevel;
//	BorderPane root;
//	//BorderPane mainRoot;
//
//	@Before
//	public void setup() {
//		//put setup info here
//		//mainRoot = Main.getRoot();
//		root = new BorderPane();
//		//root.setCenter(new Level(1));
//		//GameControllerTest.myLevel = new Level(0);
//		gameController = new GameController(root);
//		//scoreBoardController = new ScoreBoardController(root);
//		root.setCenter(myLevel);
//
//		//myLevel.startGame();
//		//gameController.gameStart();
//	}
//	
	//Some test classes for GUI will be auto-created using WindowTester
	
	@Test
	public void testMainPaneCreate() {
		//test for if main screen created
		BorderPane abc = new BorderPane();
		assertNotNull(abc);
	}
	
	@Test
	public void testEasyDifficultySettings() {
		//Test if setting changed correctly based on difficulty selected
		//Settings should match those that correspond to chosen difficulty
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));

        gameController = new GameController(root);
		gameController.setDifficulty(0);
		int difficulty = GameController.getDifficulty();
		assertEquals("Should equal 0",difficulty,0);
	}
	
	@Test
	public void testNormalDifficultySettings() {
		//Test if setting changed correctly based on difficulty selected
		//Settings should match those that correspond to chosen difficulty
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));

        gameController = new GameController(root);
		gameController.setDifficulty(1);
		int difficulty = GameController.getDifficulty();
		assertEquals("Should equal 1",difficulty,1);
	}
	
	@Test
	public void testHardDifficultySettings() {
		//Test if setting changed correctly based on difficulty selected
		//Settings should match those that correspond to chosen difficulty
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));

        gameController = new GameController(root);
		gameController.setDifficulty(2);
		int difficulty = GameController.getDifficulty();
		assertEquals("Should equal 2",difficulty,2);
		
	}
	
	@Test 
	public void testSetLifePoints(){
		 BorderPane root = new BorderPane();
	        root.setCenter(new Level(0));

	        gameController = new GameController(root);
		Label label22 = new Label();
		String s = "3";
		label22.setText(s);
		assertTrue("Stuff","3".equals(label22.getText()));
		GameController.setLifePointsTxt(10);
		assertTrue("Life POints txt should equal 10","10".equals(GameController.getLifePointsTxt()));
	}
	
	@Test
	public void testLifeDecrease() {
		//test for life decreasing when bubbles poped
		//life should decrease only if bubbles pop due to hitting other bubbles
		//hitting the wall
		GameController.setLifePointsTxt(10);
		GameController.decreaseLifePoints();
		assertEquals("Life Points should equal 9", 9, GameController.getLifePoints());
		//assertEquals("Life points should equal 9","9",GameController.getLifePointsTxt());
	}
	
	@Test
	public void testIncreaseScore() {
		//give conditions for bubble poped, should increase score properly
		//gameController.gameStart();
		GameController.setScore(0);
		GameController.increaseScore();
		assertEquals("Score should equal 1",1,GameController.getScore());
	}
	
	@Test
	public void testLifePointstoZero() {
		for(int i =0;i<10; i++ ){
			GameController.decreaseLifePoints();
		}
		assertEquals("Life Points should equal 0", 0, GameController.getLifePoints());
		assertNull(root.getCenter());
	}
	
	@Test
	public void testUserEndsGame(){
		//test for when user ends game manually

		//root.setCenter(new Level(1));
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));
        gameController = new GameController(root);
		testLevel = new Level(0);
		GameController.endGame();
		assertNull(root.getCenter());
	}
	
	@Test
	public void testGetLifepoints(){
		//change
		GameController.getLifePointsTxt();
		assertEquals("SHould be 10","10",GameController.getLifePointsTxt());
	}
	@Test
	public void testGetDifficulty(){
		gameController.setDifficulty(2);
		assertEquals("SHould be 10",2,GameController.getDifficulty());
	}
	
	public void testGameStart(){
		gameController.gameStart();
		assertEquals("LP is 10",10,gameController.getLifePoints());
	}
}