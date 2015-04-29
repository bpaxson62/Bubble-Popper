//package edu.fgcu;
//
//import edu.fgcu.utilities.Utilities;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.shape.Circle;
//import javafx.stage.Stage;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//
//public class LevelTest extends Application {
//    private static GameController gameController;
//    Level testLevel;
//
//    @BeforeClass
//    public static void initJFX() {
//        Thread t = new Thread("Initialize the JavaFX") {
//            @Override
//            public void run() {
//                Application.launch(Main.class, new String[0]);
//            }
//        };
//        t.setDaemon(true);
//        t.start();
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//
//        //put setup info here
//        // This creates the basic window, commented out for the moment
//        //This works, must remove init(primaryStage) to make it wor
//        BorderPane root = new BorderPane();
//        Group gameGroup = new Group();
//        root.setCenter(gameGroup);
//
//        gameController = new GameController(root);
//
//        ScoreBoardController scoreBoardController = new ScoreBoardController(root);
//        scoreBoardController.createToolBar(1);
//        primaryStage.setTitle("Bubble Popper");
//        primaryStage.setResizable(false);
//        primaryStage.setWidth(Configurations.MAIN_SCREEN_WIDTH + 2 * Configurations.WINDOW_BOARDER);
//        primaryStage.setHeight(Configurations.MAIN_SCREEN_HEIGHT + 2 * Configurations.WINDOW_BOARDER);
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        //scene.setFill(Color.BLACK);
//        //Button button = new Button("All Scores");
//        testLevel = new Level(1);
//
//    }
//
//    @Test
//    public void testBubbleCreate() {
//        // Test for creating a bubble
//        //Should create bubble of radius=RADIUS at coordinates supplied
//        Circle myCircle = new Circle(Configurations.radius);
//        assertNotNull(myCircle);
//        assertTrue(Configurations.radius == myCircle.getRadius());
//    }
//
//    @Test
//    public void testBubbleMinBoundries() {
//        //Test for minimum distance from walls and other bubbles
//        //before creating bubble at locations
//        testLevel = new Level(1);
//        Bubble myCircle = new Bubble(new Circle(Configurations.radius));
//        testLevel.getChildren().add(myCircle.bubble);
//        myCircle.bubble.setLayoutY(200);
//        myCircle.bubble.setLayoutX(200);
//        assertTrue(myCircle.stage == 0);
//
//    }
//
//    @Test
//    public void testBubbleClicked() {
//        //Test for bubble when user clicks it
//        //Bubble should pop and user be awarded points
//        testLevel = new Level(1);
//        Bubble myCircle = new Bubble(new Circle(Configurations.radius));
//        testLevel.getChildren().add(myCircle.bubble);
//        assertTrue(myCircle.stage == 0);
//    }
//
//    @Test
//    public void testBubbleHitsWall() {
//        //Test for when bubble hits a wall
//        //Bubble should pop and user loses life
//        testLevel = new Level(1);
//        Bubble myCircle = new Bubble(new Circle(Configurations.radius));
//        testLevel.getChildren().add(myCircle.bubble);
//        myCircle.bubble.setLayoutY(0);
//        myCircle.bubble.setLayoutX(0);
//        assertTrue(myCircle.stage == 0);
//    }
//
//    @Test
//    public void testBubbleHitsBubble() {
//        //Test for when bubbble hits another bubble
//        //Both bubbles should pop and user loses life twice
//        testLevel = new Level(1);
//        Bubble myCircle = new Bubble(new Circle(Configurations.radius));
//        Bubble myCircle2 = new Bubble(new Circle(Configurations.radius));
//        testLevel.getChildren().add(myCircle.bubble);
//        myCircle.bubble.setLayoutY(200);
//        myCircle.bubble.setLayoutX(200);
//        myCircle2.bubble.setLayoutY(200);
//        myCircle2.bubble.setLayoutX(200);
//        try{
//            testLevel.myBubbles.add(myCircle2);
//        }catch (NullPointerException e){}//scoreboard
//        testLevel.circleIntersection(myCircle, false);
//        assertTrue(myCircle.stage == 2);
//    }
//
//    @Test
//    public void testRandom() {
//        //Test random points creation
//        //Should not receive same points over and over again
//        int y = Utilities.randInt(0, Configurations.MAIN_SCREEN_HEIGHT);
//        int x = Utilities.randInt(0, Configurations.MAIN_SCREEN_WIDTH);
//        assertTrue(y > -1 && y < Configurations.MAIN_SCREEN_HEIGHT + 1);
//        assertTrue(x > -1 && x < Configurations.MAIN_SCREEN_WIDTH + 1);
//    }
//}
