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
import java.util.Stack;

/**
 * Created by brian on 3/12/2015.
 */
public class Level extends Pane {
    public Stack<Circle> circlesOn;
    public ArrayList<Circle> circlesInProgress;
    public ArrayList<Circle> circlesComplete;
    private Integer i = 0;
    private Integer answer = 0;
    private Text myEquationText;
    private Rectangle scoreRectangle;
    private Button scoreButton;
    private int difficulty;
    private Timeline growTimeline;
    private Timeline spawnTimeline;
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
    }


    private void initializeLevelTimeline() {
        growTimeline = new Timeline();
        spawnTimeline = new Timeline();
        final StackPane tempPane;
        spawnTimeline.setCycleCount(Timeline.INDEFINITE);
        growTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf;
        KeyFrame kf2;
        kf = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
//                for (int i = 0; i < circlesOn.size(); i++) {
//                    checkBounds(circlesOn.get(i));
//                    circleIntersection(circlesOn.get(i));
//                    circlesOn.get(i).setRadius(circlesOn.get(i).getRadius() + 1);
                System.out.println("hi");

                boolean isSafe = false;
                Circle myCircle = circlesOn.pop();
                myCircle.setVisible(false);
                while (isSafe != true) {

                    int boundX = Utilities.randInt(0, (int) getBoundsInParent().getMaxX());
                    int boundY = Utilities.randInt(0, (int) getBoundsInParent().getMaxY());
                    myCircle.setCenterX(boundX);
                    myCircle.setLayoutY(boundY);
                    isSafe = circleIntersection(myCircle, true);
                }
                myCircle.setVisible(true);
                circlesOn.get(i).setLayoutX(getBoundsInParent().getMaxX() / 2);
                circlesOn.get(i).setLayoutY(getBoundsInParent().getMaxY() / 2);


//                    circlesOn.get(i).pane.setTranslateX(circlesOn.get(i).pane.getTranslateX() + circlesOn.get(i).directionX * 5);
//                    circlesOn.get(i).pane.setTranslateY(circlesOn.get(i).pane.getTranslateY() + circlesOn.get(i).directionY * 5);
//                }

            }
        });
        kf2 = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for (int i = 0; i < circlesOn.size(); i++) {
                    checkBounds(circlesOn.get(i));
//                    circleIntersection(circlesOn.get(i));
//                    System.out.println(circlesOn.get(i).getBoundsInParent().getMaxY());
//                    System.out.println(circlesOn.get(i).getBoundsInParent().getMaxX());
                    circlesOn.get(i).setRadius(circlesOn.get(i).getRadius() + 1);

//                    circlesOn.get(i).pane.setTranslateX(circlesOn.get(i).pane.getTranslateX() + circlesOn.get(i).directionX * 5);
//                    circlesOn.get(i).pane.setTranslateY(circlesOn.get(i).pane.getTranslateY() + circlesOn.get(i).directionY * 5);
                }

            }
        });
        spawnTimeline.getKeyFrames().add(kf);
        growTimeline.getKeyFrames().add(kf2);
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
        circlesOn = new Stack<Circle>();
        circlesInProgress = new ArrayList<Circle>();
        circlesComplete = new ArrayList<Circle>();
        for (int i = 0; i < circleNum; i++) {
            Circle myCircle = new Circle(Configurations.radius);
            Glow myGlow = new Glow();
            myGlow.setLevel(.08);

            myCircle.setEffect(new Lighting());
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
                    circlesComplete.add(circlesInProgress.get(myIndex));
                    getChildren().remove(circlesInProgress.get(myIndex));
                    System.out.println("hello");
//                    if (answer.equals(Integer.parseInt(circlesOn.get(myIndex).myText.getText()))) {
////                        addScore();
//
//                    } else {
////                        circlesOn.get(myIndex).ball.setFill(Color.RED);
////                        subtractScore();
//                    }

                    //}
                }
            };
            myCircle.setStyle(Configurations.circleStyle);

            myCircle.addEventHandler(MouseEvent.MOUSE_PRESSED, myAction);
            circlesOn.add(i, myCircle);
        }
    }

    public void initializeLevel() {
        Random rand = new Random();
        getChildren().addAll(circlesOn);
        initializeLevelTimeline();
        play();


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
//                for (int i = 0; i < circlesOn.size(); i++) {
//                    checkBounds(circlesOn.get(i));
//                    circleIntersection(circlesOn.get(i));
//                    circlesOn.get(i).setTranslateX(circlesOn.get(i).getTranslateX() + circlesOn.get(i).directionX * 5);
//                    circlesOn.get(i).setTranslateY(circlesOn.get(i).getTranslateY() + circlesOn.get(i).directionY * 5);
//                }
//
//            }
//        });
//        timeline.getKeyFrames().add(kf);
//    }

    private void incrementScore() {

    }

    private void decrementScore() {

    }


    private int calcScoreRatio(Circle myCirle) {
        return 0;
    }

    public void play() {
        growTimeline.play();
        spawnTimeline.play();
//        timer.start();
    }


    public void stop() {
        growTimeline.stop();
        spawnTimeline.stop();
    }


    public boolean circleIntersection(Circle myBall, boolean peek) {
        Shape block = myBall;
        boolean safe = true;
        for (final Circle myShape : circlesOn) {
            if (myShape != block) {


                Shape intersect = Shape.intersect(block, myShape);

                if (intersect.getBoundsInLocal().getWidth() != -1 || intersect.getBoundsInLocal().getHeight() != -1 && !peek) {
                    circlesInProgress.remove(myBall);
                    circlesInProgress.remove(myShape);
                    circlesComplete.add(myBall);
                    circlesComplete.add(myShape);
                    decrementScore();
                    decrementScore();

                } else if (intersect.getBoundsInLocal().getWidth() != -1 || intersect.getBoundsInLocal().getHeight() != -1 && !peek) {
                    safe = false;
                }
            }
        }
        return safe;
    }

    public void checkBounds(Circle myBall) {
        /*
        if ball hits walls
         */

        if (myBall.getBoundsInParent().getMinX() <= getBoundsInParent().getMinX()) {
            decrementScore();
            circlesInProgress.remove(myBall);
            circlesComplete.add(myBall);
        } else if (myBall.getBoundsInParent().getMaxX() >= getBoundsInParent().getMaxX()) {
            decrementScore();
            circlesInProgress.remove(myBall);
            circlesComplete.add(myBall);
        } else if (myBall.getBoundsInParent().getMinY() <= getBoundsInParent().getMinY()) {
            decrementScore();
            circlesInProgress.remove(myBall);
            circlesComplete.add(myBall);
        } else if (myBall.getBoundsInParent().getMaxY() >= getBoundsInParent().getMaxY()) {
            decrementScore();
            circlesInProgress.remove(myBall);
            circlesComplete.add(myBall);
        }
    }

    public void resetGame() {

        for (int i = 0; i < circlesInProgress.size(); i++) {
            circlesOn.add(circlesInProgress.get(i));
        }
        growTimeline.playFromStart();
        spawnTimeline.playFromStart();
    }


}

class Ball {

    public Ball(Circle myCircle/*, int directionY, int directionX, Text myText*/) {
        ball = myCircle;
        this.myText = myText;
    }

    boolean activeBound;
    public StackPane pane;
    Circle ball;
    int directionY;
    int directionX;
    Text myText;

}