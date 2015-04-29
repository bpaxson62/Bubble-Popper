package edu.fgcu;

import edu.fgcu.utilities.Utilities;
import javafx.animation.FillTransition;
import javafx.animation.FillTransitionBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by brian on 3/12/2015.
 */
public class Level extends Pane {
    public ArrayList<Bubble> myBubbles;
    private int difficulty = 1;
    private static int difficultySpawn = 1000;
    private Timeline growTimeline;
    private Timeline colorTimeline;
    private Timeline spawnTimeline;
    private Timeline collisionTimeline;
    private static final GameController gameController = Main.getGameController();
    private Canvas myCanvas;
    private BorderPane root;

    public Level(int Difficulty) {
        root = GameController.getRoot();
        Random rand = new Random();
        populateCircles(100);
        int backGround = rand.nextInt(4);
        System.out.println(backGround);
        String myBackGround = "";
        if (backGround == 0) {
            myBackGround = "1.jpg";
        } else if (backGround == 1) {
            myBackGround = "2.png";
        } else if (backGround == 2) {
            myBackGround = "3.png";
        } else if (backGround == 3) {
            myBackGround = "4.jpg";
        }
        difficulty = Difficulty;
        File picture = new File(System.getProperty("user.dir") + "\\src\\Media\\" + myBackGround);
        InputStream is = null;
        try {
            is = new FileInputStream(picture);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image myImage = new Image(is);
        setBackground(new Background(new BackgroundImage(myImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        myCanvas = new Canvas(Configurations.MAIN_SCREEN_WIDTH, Configurations.MAIN_SCREEN_HEIGHT);
        getChildren().add(myCanvas);
        initializeLevel();
        stopGameInit();
    }
    public static int getDifficultySpawn(){
    	return difficultySpawn;
    }

    private void initializeLevelTimeline() {
        growTimeline = new Timeline();
        spawnTimeline = new Timeline();
        collisionTimeline = new Timeline();
        colorTimeline = new Timeline();
        colorTimeline.setCycleCount(Timeline.INDEFINITE);
        spawnTimeline.setCycleCount(Timeline.INDEFINITE);
        growTimeline.setCycleCount(Timeline.INDEFINITE);
        collisionTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf;
        KeyFrame kf2;
        KeyFrame kf3;
        kf = new KeyFrame(Duration.millis(difficultySpawn), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                boolean isSafe = false;
                Bubble myBubble = null;
                boolean find = false;
                for (int i = 0; i < myBubbles.size(); i++) {
                    if (myBubbles.get(i).stage == 0) {
                        myBubble = myBubbles.get(i);
                        find = true;
                    }
                }
                if (find == false) {
                    stopGame();
                } else {

                    myBubble.deactivate();
                    while (isSafe != true) {

                        int boundX = Utilities.randInt(200, (int) getBoundsInLocal().getMaxX() - 200);
                        int boundY = Utilities.randInt(200, (int) getBoundsInLocal().getMaxY() - 200);
                        myBubble.bubble.setCenterX(boundX);
                        myBubble.bubble.setLayoutY(boundY);
                        isSafe = circleIntersection(myBubble, true);
                    }
                    myBubble.activate();
                }
            }
        });
        kf2 = new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
        	
            @SuppressWarnings("deprecation")
			public void handle(ActionEvent event) {
            	
                for (int i = 0; i < myBubbles.size(); i++) {
                    if (myBubbles.get(i).stage == 1) {
                        if (myBubbles.get(i).bubble.getRadius() > 100) {
                            decrementScore();
                            myBubbles.get(i).deactivate();
                            
                        } else {
                            myBubbles.get(i).bubble.setRadius(myBubbles.get(i).bubble.getRadius() + difficulty);
                            
                            
                            
                        }
                        
                    }
                }

            }
            
        });
        kf3 = new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                for (int i = 0; i < myBubbles.size(); i++) {
                    if (myBubbles.get(i).stage == 1) {
                        if (myBubbles.get(i).stage == 1) {
                            circleIntersection(myBubbles.get(i), false);
                        }
                    }
                }

            }
        });

        spawnTimeline.getKeyFrames().add(kf);
        growTimeline.getKeyFrames().addAll(kf2);
        collisionTimeline.getKeyFrames().add(kf3);
    }

	@SuppressWarnings("deprecation")
	public void populateCircles(int circleNum) {
        EventHandler<MouseEvent> myAction;
        myBubbles = new ArrayList<Bubble>();
        for (int i = 0; i < circleNum; i++) {
            Bubble myCircle = new Bubble(new Circle(Configurations.radius));
            Glow myGlow = new Glow();
            myGlow.setLevel(.08);
            myCircle.bubble.setEffect(new Lighting());
            final int myIndex = i;
            myAction = new EventHandler<MouseEvent>() {

                public void handle(MouseEvent event) {
                    incrementScore();
                    myBubbles.get(myIndex).deactivate();

                }
            };
            myCircle.bubble.setVisible(false);
            myCircle.bubble.addEventHandler(MouseEvent.MOUSE_PRESSED, myAction);
            myBubbles.add(i, myCircle);
        }
    }

    public void initializeLevel() {
        for (Bubble bubble : myBubbles) {
            getChildren().add(bubble.bubble);
        }
        initializeLevelTimeline();
        startGame();
        stopGameInit();
    }


    private void incrementScore() {
        GameController.increaseScore();
    }

    private void decrementScore() {
        GameController.decreaseLifePoints();
    }

    public void play() {
        growTimeline.play();
        spawnTimeline.play();
        collisionTimeline.play();
    }

    public boolean circleIntersection(Bubble myBall, boolean peek) {
        Circle block = myBall.bubble;
        boolean safe = true;
        for (final Bubble myShape : myBubbles) {
            if (myShape.bubble != block) {

                Shape intersect = Shape.intersect(block, myShape.bubble);

                if (intersect.getBoundsInLocal().getWidth() != -1 || intersect.getBoundsInLocal().getHeight() != -1 && peek == false && myShape.stage == 1) {
                    System.out.println("delete ball");
                    myShape.deactivate();
                    myBall.deactivate();

                } else if (intersect.getBoundsInLocal().getWidth() != -1 || intersect.getBoundsInLocal().getHeight() != -1 && peek == true && myShape.stage == 1) {
                    safe = false;
                } else if (Math.abs(myBall.bubble.getCenterX() - myShape.bubble.getCenterX()) > 100 && Math.abs(myBall.bubble.getCenterY() - myShape.bubble.getCenterY()) > 100 && myShape.stage == 1) {
                    safe = false;
                }
            }
        }
        return safe;
    }

    public void checkBounds(Bubble myBall) {
        /*
        if ball hits walls
         */
        if (myBall.bubble.getBoundsInParent().getMinX() <= getBoundsInParent().getMinX()) {
            decrementScore();
            myBall.deactivate();
        } else if (myBall.bubble.getBoundsInParent().getMaxX() >= getBoundsInParent().getMaxX()) {
            decrementScore();
            myBall.deactivate();
        } else if (myBall.bubble.getBoundsInParent().getMinY() <= getBoundsInParent().getMinY()) {
            decrementScore();
            myBall.deactivate();
        } else if (myBall.bubble.getBoundsInParent().getMaxY() >= getBoundsInParent().getMaxY()) {
            decrementScore();
            myBall.deactivate();
        }
    }

    public void stopGameInit() {

        for (int i = 0; i < myBubbles.size(); i++) {
            myBubbles.get(i).reset();
        }
        growTimeline.stop();
        spawnTimeline.stop();
        collisionTimeline.stop();
    }

    public void stopGame() {
        for (int i = 0; i < myBubbles.size(); i++) {
            myBubbles.get(i).reset();
        }
        growTimeline.stop();
        spawnTimeline.stop();
        collisionTimeline.stop();
        GifCreator.stopRecording();
    }

	public void startGame() {
        GifCreator.startRecord();
        int diff = gameController.getDifficulty();
        if (diff == 0) {
            difficulty = 1;
            difficultySpawn = 1000;
        } else if (diff == 1) {
            difficulty = 2;
            difficultySpawn = 600;
        } else {
            difficulty = 3;
            difficultySpawn = 350;
        }
        for (int i = 0; i < myBubbles.size(); i++) {
            myBubbles.get(i).reset();
            
        }
        growTimeline.playFromStart();
        spawnTimeline.playFromStart();
        collisionTimeline.playFromStart();
    }
    

}

class Bubble extends Shape{
    private FillTransition fillTransition;
    @SuppressWarnings("deprecation")
	public Bubble(Circle myCircle/*, int directionY, int directionX, Text myText*/) {
        bubble = myCircle;
        fillTransition = FillTransitionBuilder.create().duration(Duration.millis(Level.getDifficultySpawn()))
       		.shape(bubble)
       		.fromValue(Color.GREEN)
       		.toValue(Color.RED)
       		.cycleCount(Timeline.INDEFINITE)
       		.autoReverse(false)
       		.build();
       fillTransition.playFromStart();
       fillTransition.stop();
        stage = 0;
    }

    public void activate() {
        stage = 1;
        bubble.setVisible(true);
        fillTransition.playFromStart();
    }

    public void deactivate() {
        stage = 2;
        bubble.setVisible(false);
        fillTransition.stop();
    }

    public void reset() {
        stage = 0;
        bubble.setVisible(false);
        bubble.setRadius(25);
        fillTransition.stop();
        fillTransition.playFromStart();
    }
    int stage;
    Circle bubble;
	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		// TODO Auto-generated method stub
		return null;
	}
}