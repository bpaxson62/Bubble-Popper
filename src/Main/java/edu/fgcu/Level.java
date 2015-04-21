package edu.fgcu;

import edu.fgcu.utilities.Utilities;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
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
    //    public ArrayList<Bubble> circlesInProgress;
//    public ArrayList<Bubble> circlesComplete;
    private Integer i = 0;
    private Integer answer = 0;
    private Text myEquationText;
    private Rectangle scoreRectangle;
    private Button scoreButton;
    private int difficulty;
    private Timeline growTimeline;
    private Timeline spawnTimeline;
    private Timeline collisionTimeline;
    private AnimationTimer timer;
    private static final GameController gameController = Main.getGameController();
    private Group myGroup;

    public Level(int Difficulty) {
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
        difficulty = 0;
        File picture = new File(System.getProperty("user.dir") + "\\src\\Media\\" + myBackGround);

        InputStream is = null;
        try {
            is = new FileInputStream(picture);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image myImage = new Image(is);

//        myGroup = new Group();


        setBackground(new Background(new BackgroundImage(myImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

//        getChildren().addAll(myGroup);

        //getChildren().add(myGroup);
//        populateCircles(100);
        initializeLevel();
        stopGame();
    }


    private void initializeLevelTimeline() {
        growTimeline = new Timeline();
        spawnTimeline = new Timeline();
        collisionTimeline = new Timeline();

        final StackPane tempPane;
        spawnTimeline.setCycleCount(Timeline.INDEFINITE);
        growTimeline.setCycleCount(Timeline.INDEFINITE);
        collisionTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf;
        KeyFrame kf2;
        KeyFrame kf3;
        kf = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
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

                        int boundX = Utilities.randInt(100, (int) getBoundsInParent().getMaxX() - 100);
                        int boundY = Utilities.randInt(100, (int) getBoundsInParent().getMaxY() - 100);
                        myBubble.bubble.setCenterX(boundX);
                        myBubble.bubble.setLayoutY(boundY);
                        isSafe = circleIntersection(myBubble, true);
                    }
                    myBubble.activate();
                }
            }
        });
        kf2 = new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                for (int i = 0; i < myBubbles.size(); i++) {
                    if (myBubbles.get(i).stage == 1) {
                        if (myBubbles.get(i).bubble.getRadius() > 100) {
                            myBubbles.get(i).deactivate();
                        } else {
                            myBubbles.get(i).bubble.setRadius(myBubbles.get(i).bubble.getRadius() + 1);
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

    public void startGif() {
//        Circle circle = new Circle(25, 25, 25);
//        circle.setCenterX(500);
//        circle.setCenterY(500);
//        circle.setFill(Color.ORANGE);
    }

    public void stopGif() {

    }

    public void populateCircles(int circleNum) {
        EventHandler<MouseEvent> myAction;
        myBubbles = new ArrayList<Bubble>();
//        circlesInProgress = new ArrayList<Bubble>();
//        circlesComplete = new ArrayList<Bubble>();
        for (int i = 0; i < circleNum; i++) {
            Bubble myCircle = new Bubble(new Circle(Configurations.radius));
            Glow myGlow = new Glow();
            myGlow.setLevel(.08);

            myCircle.bubble.setEffect(new Lighting());
//            myCircle.setEffect(myGlow);
//            Lighting lighting = new Lighting();
//            Light myLight = new Light();
//            lighting.setLight(new L);
//            myCircle.setEffect(new Lighting());
//            Ball myBall = new Ball();
            final int myIndex = i;
            myAction = new EventHandler<MouseEvent>() {

                public void handle(MouseEvent event) {
                    incrementScore();

                    myBubbles.get(myIndex).deactivate();

//
//                    circlesComplete.add(circlesInProgress.get(myIndex));
//                    getChildren().remove(circlesInProgress.get(myIndex));

//                    if (answer.equals(Integer.parseInt(myBubbles.get(myIndex).myText.getText()))) {
////                        addScore();
//
//                    } else {
////                        myBubbles.get(myIndex).ball.setFill(Color.RED);
////                        subtractScore();
//                    }

                    //}
                }
            };
            myCircle.bubble.setStyle(Configurations.circleStyle);
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
        play();
        stopGame();


        double x = 100;
        double y = Configurations.MAIN_SCREEN_HEIGHT / 4;


    }
//
//    public void initTimeline() {
//        timeline = new Timeline();
//        final StackPane tempPane;
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        KeyFrame kf;
//        kf = new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                for (int i = 0; i < myBubbles.size(); i++) {
//                    checkBounds(myBubbles.get(i));
//                    circleIntersection(myBubbles.get(i));
//                    myBubbles.get(i).setTranslateX(myBubbles.get(i).getTranslateX() + myBubbles.get(i).directionX * 5);
//                    myBubbles.get(i).setTranslateY(myBubbles.get(i).getTranslateY() + myBubbles.get(i).directionY * 5);
//                }
//
//            }
//        });
//        timeline.getKeyFrames().add(kf);
//    }

    private void incrementScore() {
    	GameController.increaseScore();
    }

    private void decrementScore() {
    	GameController.decreaseLifePoints();
    }


    private int calcScoreRatio(Circle myCirle) {
        return 0;
    }

    public void play() {
        growTimeline.play();
        spawnTimeline.play();
        collisionTimeline.stop();
        
//        timer.start();
    }


    public void stop() {
        growTimeline.stop();
        spawnTimeline.stop();
        collisionTimeline.stop();
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
                    decrementScore();
                    decrementScore();

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


    public void stopGame() {

        for (int i = 0; i < myBubbles.size(); i++) {
            myBubbles.get(i).reset();
        }
        growTimeline.stop();
        spawnTimeline.stop();
        collisionTimeline.stop();
    }

    public void startGame() {
        for (int i = 0; i < myBubbles.size(); i++) {
            myBubbles.get(i).reset();
        }
        growTimeline.playFromStart();
        spawnTimeline.playFromStart();
        collisionTimeline.playFromStart();
    }


}

class Bubble {

    public Bubble(Circle myCircle/*, int directionY, int directionX, Text myText*/) {
        bubble = myCircle;
        this.myText = myText;
        stage = 0;
    }


    public enum status {
        STAGING, PLAYING, FINISHED
    }

    public void activate() {
        stage = 1;
        bubble.setVisible(true);
    }

    public void deactivate() {
        stage = 2;
        bubble.setVisible(false);
    }

    public void reset() {
        stage = 0;
        bubble.setVisible(false);
        bubble.setRadius(25);
    }


    int stage;
    Timeline myBubble;
    boolean activeBound;
    public StackPane pane;
    Circle bubble;
    int directionY;
    int directionX;
    Text myText;

}