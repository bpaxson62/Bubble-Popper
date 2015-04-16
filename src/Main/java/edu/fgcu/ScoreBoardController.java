package edu.fgcu;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class ScoreBoardController extends Parent{

	//private Group scoreBoard;
	private static BorderPane root;
	private Group myGroup;
	private int highScore = 0;
	private int score = 0;
	private int difficulty; //Not sure if we want this as int or a string
	private ObservableList<Scores> allScores; //Store all scores here
	private static GridPane grid;
	private static CreateGridPane createGridPane;
	private static GameController gameController;
//	private static Level level;
	//static ScoreBoardController outer = new ScoreBoardController(root);
	
	//class to create object for allScores list
	public class Scores{
		private int scores;
		private String difficulty;
		
		public Scores(int scores, int difficulty){
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
	
	public static GameController gameController(){
		return gameController;
	}
	
//	public static Level level(){
//
//		return GameController.mylevel;
//	}
	
	 public static CreateGridPane getGridPane(){
	    	return createGridPane;
	    }
	
	public ScoreBoardController(BorderPane scoreBoard){
		this.root = scoreBoard;
		gameController = Main.getGameController();
//		this.level = level();
	}

	public void createToolBar(int i){
		Group myGroup = new Group();
		ToolBar toolbar = new ToolBar();
    	ChoiceBox difficulties = new ChoiceBox();
    	Button scoreBoardBtn = new Button("Score Board");
    	final Button startStopBtn = new Button("Start");
    	difficulties.getItems().addAll("Easy","Normal","Hard");
    	difficulties.getSelectionModel().selectFirst();
    	final Label label = new Label();
    	final Label scoreTxt = new Label();
    	final Label scoreValue = new Label();
    	final Label lifePointsTxt = new Label();
    	final Label lifePointsValue = new Label();
    	scoreTxt.setText("Score:");
    	scoreValue.setText("0");
    	lifePointsTxt.setText("HP:");
    	lifePointsValue.setText(GameController.getLifePointsTxt());
    	toolbar.getItems().add(scoreBoardBtn);
    	toolbar.getItems().add(difficulties);
    	toolbar.getItems().add(startStopBtn);
    	toolbar.getItems().add(scoreTxt);
    	toolbar.getItems().add(scoreValue);
    	toolbar.getItems().add(lifePointsTxt);
    	toolbar.getItems().add(lifePointsValue);
    	root.setTop(toolbar);
    	
    	//handiling button press for scoreboard creation
    	scoreBoardBtn.setOnAction(new EventHandler<ActionEvent>() {
    	    public void handle(ActionEvent e) {
    	        //label.setText("Accepted");
    	        final Stage scoreStage = new Stage();
    	        BorderPane scorePane = new BorderPane();
    	        Group rootGroup = new Group();
    	        createGridPane = new CreateGridPane(scorePane);
    	        scorePane.setCenter(rootGroup);
    	        scoreStage.setTitle("Score Board");
    	        scoreStage.setResizable(false);
    	        scoreStage.setWidth(Configurations.SCORE_SCREEN_WIDTH + 2*Configurations.WINDOW_BOARDER);
    	        scoreStage.setHeight(Configurations.SCORE_SCREEN_HEIGHT + 2*Configurations.WINDOW_BOARDER);
    	        Scene scoreScene = new Scene(scorePane);
    	        scoreStage.setScene(scoreScene);
    	        scoreStage.centerOnScreen();
    	        scorePane.setCenter(createGridPane.CreateGrid(1));
    	        scoreStage.show();
    	        
    	    }
    		});
    	
    	startStopBtn.setOnAction(new EventHandler<ActionEvent>() {
    	    public void handle(ActionEvent e) {
    	    	if(startStopBtn.getText()=="Start"){
    	    		startStopBtn.setText("Stop");
    	    		gameController.changeState(1);
    	    	}
    	    	
    	    	else{
    	    		startStopBtn.setText("Start");
					gameController.changeState(0);
    	    	}
    	    }
    		});
		
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
	
	public  void addScoreToList(int score, int difficulty){
		if (allScores != null){
			allScores.add(new Scores(score, difficulty));
		}
		
	}
	
	//GridPane to display the scores in one column and difficulty in another
	public class CreateGridPane extends Parent{
		private BorderPane score;
		
		public CreateGridPane(BorderPane scoreBoard){
		
		this.score = scoreBoard;
		
		//BorderPane score = new BorderPane();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		Text scoreTxt;
		Text difficultyTxt;
		//Setting Top grid headers and Title
		//Left column is scores, right is difficulty
		Text scoreTitle = new Text("All Scores");
		scoreTitle.setFont(Font.font("Arial",FontWeight.BOLD, 25));
		//score.setTop(scoreTitle);
		grid.add(scoreTitle,3,0); //column 2, row 1
		//addScoreToList(2,2);
		//allScores.add(new Scores(3, 3));
		
		if(allScores == null){
			scoreTxt = new Text("You have not played any games yet.");
			grid.add(scoreTxt,3,2);
			score.setTop(grid);
			return;	
		}
		
		Text scoreHeader = new Text("Score");
		scoreHeader.setFont(Font.font("Arial",FontWeight.BOLD, 20));
		grid.add(scoreHeader, 1, 1); //column 2, row 2
		Text difficultyHeader = new Text("Difficulty");
		difficultyHeader.setFont(Font.font("Arial",FontWeight.BOLD, 20));
		grid.add(difficultyHeader, 5, 1); //column 3, row 2
		//limited to last 20 scores shown
		for(int i=0;i<20;i++){
			//make this use list instead of temp
			scoreTxt =new Text("Temp"+ i);
			grid.add(scoreTxt,1,i+2);
			difficultyTxt = new Text("Dif Temp"+i);
			grid.add(difficultyTxt,5,i+2);
			
		}
		score.setTop(grid);
		return;
		}

		public Node CreateGrid(int i) {
			return null;
		}
	}
	
/*	public void handle(ActionEvent t) {
		BorderPane border = new BorderPane();
		
		Stage stage = new Stage();
		//Scene scene = new Scene();
		
		//Center Pane
        //adds Gridpane to center of BorderPane
        border.setCenter(addGridPane());
       
    }
	*/
}
