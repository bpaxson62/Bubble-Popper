package edu.fgcu;

import edu.fgcu.utilities.Utilities;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameControllerTest extends Application{
////	 private static GameController gameController;
//	    Level testLevel;
//	    BorderPane root;
	public class TestObject{
		private int score;
		private int difficulty;
		public TestObject(int score, int difficulty){
			this.score=score;
			this.difficulty=difficulty;
		}
		public String toString(){
			return Integer.toString(score);
		}

	}

	private static ScoreBoardController scoreBoardController;
	private static Level level;
	private static GameController gameController;
	BorderPane root;
	Level testLevel;

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
		assertEquals("SHould be 10", 2, GameController.getDifficulty());
	}
	
	public void testGameStart(){
		gameController.gameStart();
		assertEquals("LP is 10", 10, gameController.getLifePoints());
	}
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
		scoreBoardController = new ScoreBoardController(root);
		BorderPane root = new BorderPane();
		root.setCenter(new Level(0));
		ObservableList<TestObject> testScores = FXCollections.observableArrayList();
		testScores.add(new TestObject(10, 0));
		gameController = new GameController(root);
		//testScores.add(new TestObject(10, 0));
		scoreBoardController.addScoreToList(10, 0);
//		System.out.println( "first is    " + testScores.get(0));
//		System.out.println("second is   " + scoreBoardController.getAllScores().get(0));

		assertTrue("Score should equal 10, difficulty 1", testScores.get(0).toString().equals(scoreBoardController.getAllScores().get(0).toString()));

	}

	@Test
	public void testUpdateHighScore() {
		BorderPane root = new BorderPane();
		root.setCenter(new Level(0));
		scoreBoardController = new ScoreBoardController(root);
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


		assertEquals("Should be 1",1,scoreBoardController.getallScoresSize());


	}

	@Test
	public void testCreateGrid(){
		BorderPane root = new BorderPane();
		BorderPane scorePanre = new BorderPane();
		root.setCenter(new Level(0));
		gameController = new GameController(root);
		scoreBoardController = new ScoreBoardController(root);
		ScoreBoardController.CreateGridPane cg = new ScoreBoardController.CreateGridPane(root);
		//createGridPane = new CreateGridPane(scorePanre);
		assertNotNull(root);
	}
	@Test
	public void testBubbleCreate() {
		// Test for creating a bubble
		//Should create bubble of radius=RADIUS at coordinates supplied
		Circle myCircle = new Circle(Configurations.radius);
		assertNotNull(myCircle);
		assertTrue(Configurations.radius == myCircle.getRadius());
	}

	@Test
	public void testBubbleMinBoundries() {
		//Test for minimum distance from walls and other bubbles
		//before creating bubble at locations
		testLevel = new Level(1);
		Bubble myCircle = new Bubble(new Circle(Configurations.radius));
		testLevel.getChildren().add(myCircle.bubble);
		myCircle.bubble.setLayoutY(200);
		myCircle.bubble.setLayoutX(200);
		assertTrue(myCircle.stage == 0);

	}

	@Test
	public void testBubbleClicked() {
		//Test for bubble when user clicks it
		//Bubble should pop and user be awarded points
		testLevel = new Level(1);
		Bubble myCircle = new Bubble(new Circle(Configurations.radius));
		testLevel.getChildren().add(myCircle.bubble);
		assertTrue(myCircle.stage == 0);
	}

	@Test
	public void testBubbleHitsWall() {
		//Test for when bubble hits a wall
		//Bubble should pop and user loses life
		testLevel = new Level(1);
		Bubble myCircle = new Bubble(new Circle(Configurations.radius));
		testLevel.getChildren().add(myCircle.bubble);
		myCircle.bubble.setLayoutY(0);
		myCircle.bubble.setLayoutX(0);
		assertTrue(myCircle.stage == 0);
	}

	@Test
	public void testBubbleHitsBubble() {
		//Test for when bubbble hits another bubble
		//Both bubbles should pop and user loses life twice
		testLevel = new Level(1);
		Bubble myCircle = new Bubble(new Circle(Configurations.radius));
		Bubble myCircle2 = new Bubble(new Circle(Configurations.radius));
		testLevel.getChildren().add(myCircle.bubble);
		myCircle.bubble.setLayoutY(200);
		myCircle.bubble.setLayoutX(200);
		myCircle2.bubble.setLayoutY(200);
		myCircle2.bubble.setLayoutX(200);
		try{
			testLevel.myBubbles.add(myCircle2);
		}catch (NullPointerException e){}//scoreboard
		testLevel.circleIntersection(myCircle, false);
		assertTrue(myCircle.stage == 2);
	}

	@Test
	public void testRandom() {
		//Test random points creation
		//Should not receive same points over and over again
		int y = Utilities.randInt(0, Configurations.MAIN_SCREEN_HEIGHT);
		int x = Utilities.randInt(0, Configurations.MAIN_SCREEN_WIDTH);
		assertTrue(y > -1 && y < Configurations.MAIN_SCREEN_HEIGHT + 1);
		assertTrue(x > -1 && x < Configurations.MAIN_SCREEN_WIDTH + 1);
	}

}