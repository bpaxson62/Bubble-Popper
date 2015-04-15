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
    
    public static GameController getGameController(){
    	return gameController;
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
    	//scene.setFill(Color.BLACK);
    	ToolBar toolbar = new ToolBar();
    	ChoiceBox difficulties = new ChoiceBox();
    	Button scoreBoardBtn = new Button("Score Board");
    	difficulties.getItems().addAll("Easy","Normal","Hard");
    	final Label label = new Label();
    	toolbar.getItems().add(scoreBoardBtn);
    	toolbar.getItems().add(difficulties);
    	toolbar.getItems().add(label);
    	root.setTop(toolbar);
    	scoreBoardBtn.setOnAction(new EventHandler<ActionEvent>() {
    	    public void handle(ActionEvent e) {
    	        label.setText("Accepted");
    	        final Stage scoreStage = new Stage();
    	        BorderPane scorePane = new BorderPane();
    	        Group rootGroup = new Group();
    	        scorePane.setCenter(rootGroup);
    	        scoreStage.setTitle("Score Board");
    	        scoreStage.setResizable(false);
    	        scoreStage.setWidth(Configurations.MAIN_SCREEN_WIDTH + 2*Configurations.WINDOW_BOARDER);
    	        scoreStage.setHeight(Configurations.MAIN_SCREEN_HEIGHT + 2*Configurations.WINDOW_BOARDER);
    	        Scene scoreScene = new Scene(scorePane);
    	        scoreStage.setScene(scoreScene);
    	        scoreStage.centerOnScreen();
    	        scorePane.setCenter(ScoreBoardController.addGridPane());
    	        scoreStage.show();
    	        
    	    }
    		});
        primaryStage.show();
        gameController.changeState(1);

    }
    

    public static void main(String[] args) { launch(args); }

}
