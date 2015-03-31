package edu.fgcu;


import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private ArrayList<ScaleTransition> scaleTransition;
    private static GameController gameController;
    
    public static GameController getGameController(){
    	return gameController;
    }


    public double getSampleWidth() { return 150; }

    public double getSampleHeight() { return 150; }

    @Override public void start(Stage primaryStage) throws Exception {
    	// This creates the basic window, commented out for the moment
    	//This works, must remove init(primaryStage) to make it work
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
        primaryStage.show();
       gameController.changeState(1);

    }
    

    public static void main(String[] args) { launch(args); }

}
