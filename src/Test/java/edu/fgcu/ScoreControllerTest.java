package edu.fgcu;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreControllerTest extends Application{
	private static ScoreBoardController scoreBoardController;
	private static Level level;
	private static GameController gameController;
	BorderPane root;
	Level testLevel;
	public class TestObject{
		private int score;
		private int difficulty;
		public TestObject(int score, int difficulty){
			this.score=score;
			this.difficulty=difficulty;
		}
	}

	protected ObservableList<TestObject> testScores;
	public void addTestScores(){
		if(testScores!=null){
		testScores.add(new TestObject(10, 0));
		}
	}
	
	    

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
	
	/*
	@Before
	public void setup() {
	root = new BorderPane();
	gameController = new GameController(root);
	scoreBoardController = new ScoreBoardController(root);
	level = new Level(0);
	GameControllerTest.myLevel = new Level(0);
	scoreBoardController.createToolBar(1);
	gameController = Main.getGameController();
	
	}
	*/
	//Some test classes for GUI will be auto-created using WindowTester
	
	@Test
	public void testScoreboardCreate() {
		//test for if scoreboard is created when button to view is clicked
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));

        gameController = new GameController(root);
        scoreBoardController = new ScoreBoardController(root);
		assertNotNull(root);
	}
	
	@Test
	public void testAddLastScore() {
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));

        gameController = new GameController(root);
        scoreBoardController = new ScoreBoardController(root);
		scoreBoardController.addScoreToList(10, 0);
		addTestScores();
		//testScores.add(new TestObject(10, 0));
		assertEquals("Score should equal 10, difficulty 1", testScores, scoreBoardController.getAllScores());
		
	}
	
	@Test
	public void testUpdateHighScore() {
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));

        gameController = new GameController(root);
		ScoreBoardController.setHighScore(10);
		int newScoreLower = 5;
		int newScoreHigher = 20;
		//scoreBoardController.comapreScore(newScoreLower);
		ScoreBoardController.setHighScore(newScoreLower);
		assertEquals("Should be 10", 10, scoreBoardController.getHighScore() );
		
		ScoreBoardController.setHighScore(newScoreHigher);
		assertEquals("Should be 20",20, scoreBoardController.getHighScore() );
		
	}
	
	@Test
	public void testGetSizeFunction(){
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));
        gameController = new GameController(root);
        scoreBoardController = new ScoreBoardController(root);
        
        scoreBoardController.addScoreToList(10, 0);
		assertEquals("Should be 1",2,scoreBoardController.getallScoresSize());
		
		
	}
	
	@Test
	public void testCreateGrid(){
		BorderPane root = new BorderPane();
        root.setCenter(new Level(0));
        gameController = new GameController(root);
		ScoreBoardController.setHighScore(10);
		assertNotNull(root);
	}
	

	 
}
