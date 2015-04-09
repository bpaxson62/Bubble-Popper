package edu.fgcu;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;


public class LevelTest {
	private static GameController gameController;
	Level testLevel;
	@Before
	public void setup() {
			//put setup info here
		// This creates the basic window, commented out for the moment
		//This works, must remove init(primaryStage) to make it work
		Stage primaryStage = new Stage();
		BorderPane root = new BorderPane();
		Group gameGroup = new Group();
		root.setCenter(gameGroup);

		gameController = new GameController(root);
		primaryStage.setTitle("Bubble Popper");
		primaryStage.setResizable(false);
		primaryStage.setWidth(Configurations.MAIN_SCREEN_WIDTH + 2*Configurations.WINDOW_BOARDER);
		primaryStage.setHeight(Configurations.MAIN_SCREEN_HEIGHT + 2*Configurations.WINDOW_BOARDER);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		//scene.setFill(Color.BLACK);
		//Button button = new Button("All Scores");
		testLevel = new Level(1);

	}
	
	@Test
	public void testBubbleCreate() {
		// Test for creating a bubble
		//Should create bubble of radius=RADIUS at coordinates supplied
		testLevel.circlesOn.size();
	}
	
	@Test
	public void testBubbleMinBoundries() {
		//Test for minimum distance from walls and other bubbles
		//before creating bubble at locations
		throw new RuntimeException();
		
	}
	
	@Test
	public void testBubbleClicked() {
		//Test for bubble when user clicks it
		//Bubble should pop and user be awarded points
		throw new RuntimeException();
	}
	
	@Test
	public void testBubbleHitsWall() {
		//Test for when bubble hits a wall
		//Bubbble should pop and user loses life
		throw new RuntimeException();
	}
	
	@Test
	public void testBubbleHitsBubble() {
		//Test for when bubbble hits another bubble
		//Both bubbles should pop and user loses life twice
		throw new RuntimeException();
	}
	
	@Test
	public void testRandom() {
		//Test random points creation
		//Should not receive same points over and over again
		throw new RuntimeException();
	}
}
