package bubblePopper;

import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScoreBoardController{

	private Group scoreBoard;
	private int highScore = 0;
	private int score = 0;
	private int difficulty; //Not sure if we want this as int or a string
	private ObservableList<Scores> allScores; //Store all scores here
	
	
	//class to create object for allScores list
	public class Scores{
		private int scores;
		private String difficulty;
		
		Scores(int scores, int difficulty){
			this.scores=scores;
			switch(difficulty){
			case 1: this.difficulty = Configurations.EASY_DIFFICULTY;
				break;
			case 2: this.difficulty = Configurations.NORMAL_DIFFICULTY;
				break;
			case 3: this.difficulty = Configurations.HARD_DIFFICULTY;
				break;
			}
		}
	}
	
	public ScoreBoardController(Group scoreBoard){
		this.scoreBoard = scoreBoard;
	}
	
	


	//Get Methods
	public int getHighScore(){
		return highScore;
		}
	
	public List<Scores> getAllScores(){
		return allScores;
	}
	
	//Set Methods
	public void setScore(int score){
		this.score = score;
	}
	
	public void setHighScore(int highScore){
		this.highScore = highScore;
		}
	
	
	public void comapreScore(int newScore){
		//setup method to compare current highScore to newScore
		if (newScore > this.highScore){
			this.highScore = newScore;
		}
	}
	
	//Adds score with difficulty setting to list
	
	public void addScoreToList(int score, int difficulty){
		if (allScores != null){
			allScores.add(new Scores(score, difficulty));
		}
		
	}
	
	//GridPane to display the scores in one column and difficulty in another
	public GridPane addGridPane(){
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		Text scoreTxt;
		Text difficultyTxt;
		//Setting Top grid headers and Title
		//Left column is scores, right is difficulty
		Text scoreTitle = new Text("All Scores");
		scoreTitle.setFont(Font.font("Arial",FontWeight.BOLD, 25));
		grid.add(scoreTitle,1,0); //column 2, row 1
		
		Text scoreHeader = new Text("Score");
		scoreHeader.setFont(Font.font("Arial",FontWeight.BOLD, 20));
		grid.add(scoreHeader, 1, 1); //column 2, row 2
		
		Text difficultyHeader = new Text("Difficulty");
		difficultyHeader.setFont(Font.font("Arial",FontWeight.BOLD, 20));
		grid.add(difficultyHeader, 2, 1); //column 3, row 2
		
		
		//Checking if list is empty
		//Have default message display if yes
		if(allScores.isEmpty()){
			scoreTxt = new Text("You have not played any games yet.");
			grid.add(scoreTxt,1,1);
			return grid;
			
		}
		
		//limited to last 20 scores shown
		for(int i=0;i<20;i++){
			//make this use list instead of temp
			scoreTxt =new Text("Temp");
			grid.add(scoreTxt,1,i+2);
		}
		
		return grid;
		
	}
	
	public void handle(ActionEvent t) {
		BorderPane border = new BorderPane();
		
		Stage stage = new Stage();
		//Scene scene = new Scene();
		
		//Center Pane
        //adds Gridpane to center of BorderPane
        border.setCenter(addGridPane());


       
    }
	
}
