package edu.fgcu;


import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    public static Canvas canvas;
    private ArrayList<ScaleTransition> scaleTransition;
    private static GameController gameController;
    private static ScoreBoardController scoreBoardController;
    
    public static GameController getGameController(){
    	return gameController;
    }
    
    public static ScoreBoardController getScoreBoardController(){
    	return scoreBoardController;
    }


    public double getSampleWidth() { return 150; }

    public double getSampleHeight() { return 150; }

    @Override public void start(Stage primaryStage) throws Exception {
    	// This creates the basic window, commented out for the moment
    	BorderPane root = new BorderPane();
        canvas = new Canvas(Configurations.MAIN_SCREEN_WIDTH + 2*Configurations.WINDOW_BOARDER,
                Configurations.MAIN_SCREEN_HEIGHT + 2*Configurations.WINDOW_BOARDER);
        GraphicsContext gc = canvas.getGraphicsContext2D();
//        drawShapes(gc);
//        canvas.snapshot(null, wim);
        root.getChildren().add(canvas);
    	//final BorderPane scorePane = new BorderPane();
        Group gameGroup = new Group();
        root.setCenter(gameGroup);
        
    	gameController = new GameController(root);
    	
    	primaryStage.setTitle("Bubble Popper");
    	primaryStage.setResizable(false);
    	primaryStage.setWidth(Configurations.MAIN_SCREEN_WIDTH + 2*Configurations.WINDOW_BOARDER);
    	primaryStage.setHeight(Configurations.MAIN_SCREEN_HEIGHT + 2*Configurations.WINDOW_BOARDER);
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
        primaryStage.show();
        
        scoreBoardController = new ScoreBoardController(root);
        scoreBoardController.createToolBar(1);

    }
    

    public static void main(String[] args) { launch(args); }

}
