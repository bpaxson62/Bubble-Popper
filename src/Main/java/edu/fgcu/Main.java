package edu.fgcu;


import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


public class Main extends Application {

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
        
        gameController.changeState(1);
        scoreBoardController = new ScoreBoardController(root);
        scoreBoardController.createToolBar(1);

    }
    

    public static void main(String[] args) { launch(args); }

}
