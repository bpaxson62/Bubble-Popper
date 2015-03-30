package bubblePopper;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Stack;

/**
 * Created by brian on 3/12/2015.
 */
public class Level extends Parent {
    private Stack<Circle> circles;
    private Timeline timeline;
    private AnimationTimer timer;
    private static final GameController gameController = Main.getGameController();
    private Group myGroup;

    public Level(int Difficulty){
        myGroup = new Group();
        getChildren().add(myGroup);
        populateCircles(100);
        initializeLevel();
    }

    private void initializeLevelTimeline(){

    }

    public void start(){
        Circle circle = new Circle(25, 25, 25);
        circle.setCenterX(500);
        circle.setCenterY(500);
        circle.setFill(Color.ORANGE);
    }
//
//    public void stop(){
//        GifCreator.stopRecording();
//    }

    public void populateCircles(int circleNum){
        circles = new Stack<Circle>();
        for(int i = 0; i < circleNum; i++){
            Circle myCircle = new Circle(25,25,25);
            circles.push(myCircle);
        }
    }

    public void initializeLevel(){
        while(circles.size()!=0){
            myGroup.getChildren().add(circles.pop());
        }
    }

    private void incrementScore(){

    }

    private int calcScoreRatio(Circle myCirle){
        return 0;
    }
    public void play(){
        timeline.stop();
        timer.stop();
    }


    public void stop(){
        timeline.stop();
        timer.stop();
    }
    //    public void init(Stage primaryStage) throws Exception {
//        // TODO Auto-generated method stub
//
//        BorderPane root = new BorderPane();
//        ToolBar toolbar = new ToolBar();
//        //toolbar.setLayoutX(100);
//        toolbar.autosize();
//        //HBox statusbar = new HBox();
//        //Node appContent = new AppContentNode();
//        root.setTop(toolbar);
//        //Scene scene = new Scene(root, 1200, 900, Color.GRAY);
//        Group myGroup = new Group();
//        root.setCenter(myGroup);
//
//        scaleTransition = new ArrayList<ScaleTransition>();
//        Scene scene = new Scene(root, 300, 250, Color.WHITE);
//        Random rand = new Random(System.currentTimeMillis());
//        for (int i = 0; i < 100; i++) {
//            int x = rand.nextInt((int) scene.getWidth());
//            int y = rand.nextInt((int) scene.getHeight());
//            int red = rand.nextInt(255);
//            int green = rand.nextInt(255);
//            int blue = rand.nextInt(255);
//            Circle circle = new Circle(25, 25, 25);
//            circle.setCenterX(x);
//            circle.setCenterY(y);
//            circle.setFill(Color.ORANGE);
//
//            ScaleTransition myTransition = ScaleTransitionBuilder.create()
//                    .node(circle)
//                    .duration(Duration.seconds(4))
//                    .toX(3)
//                    .toY(3)
//                    .cycleCount(Timeline.INDEFINITE)
//                    .autoReverse(true)
//                    .build();
//
//            scaleTransition.add(i,myTransition);
//
//            //int rot = rand.nextInt(360);
//            //text.setFill(Color.rgb(red, green, blue, .99));
//            //text.setRotate(rot);
//            myGroup.getChildren().add(circle);
//        }
//
//
//        //Scene scene = new Scene(root, 1200, 900, Color.GRAY);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//
//        primaryStage.show();
//
//    }
//
//    public void play() {
//        for (ScaleTransition transitions:scaleTransition){
//            transitions.play();
//        }
//    }
//
//    @Override public void stop() {
//        for (ScaleTransition transitions:scaleTransition){
//            transitions.stop();
//        }
//    }

}
