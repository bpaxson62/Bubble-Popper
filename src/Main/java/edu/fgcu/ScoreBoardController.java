package edu.fgcu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ScoreBoardController extends Parent{

	//private Group scoreBoard;
	private static BorderPane root;
	private Group myGroup;
	private static int highScore;
	private int score = 0;
	private int difficulty; //Not sure if we want this as int or a string
	private static ObservableList<Scores> allScores = FXCollections.observableArrayList(); //Store all scores here
	private static GridPane grid;
	private static CreateGridPane createGridPane;
	private static GameController gameController;
	private boolean firstTime = true;
	private boolean doOnce =true;
	private final Label scoreTxt = new Label();
	private final Label scoreValue = new Label();
	private final Label lifePointsTxt = new Label();
	private final Label lifePointsValue = new Label();
	private final ToolBar toolbar = new ToolBar();
	private final ToolBar bToolBar = new ToolBar();
	private int counter = 0;
	//private static int scoreTest;
	//private static String scoreTXT;
	Button scoreBoardBtn = new Button("Score Board");
	//Buttons and Choice Boxes
	private final Button startStopBtn = new Button("Start");
	private final ChoiceBox<String> difficulties = new ChoiceBox<String>(FXCollections.observableArrayList(
			"Easy","Normal","Hard"));
	
	private final Task <Void> task = new Task<Void>(){
		@Override
		protected Void call() throws Exception {
			updateMessage(GameController.getLifePointsTxt());
			return null;
		}	
	};
	
	private final Task<Void> scoreTask = new Task<Void>(){

		@Override
		protected Void call() throws Exception {
			updateMessage(GameController.getScoreTxt());
			return null;
		}
	};
	
//	private static Level level;
	//static ScoreBoardController outer = new ScoreBoardController(root);
	
	public ScoreBoardController(BorderPane scoreBoard){
		this.root = scoreBoard;
		gameController = Main.getGameController();
//		this.level = level();
	}
	
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
	public String toString(){
			return Integer.toString(scores);
		}
		
	}
	
	public static GameController gameController(){
		return gameController;
	}
	
	
	 public static CreateGridPane getGridPane(){
	    	return createGridPane;
	    }
	
	public void setHighScore(int s){
		if(s>this.highScore){
			this.highScore=s;
		}
	}

	public void createToolBar(int i){
    	if (difficulties.getValue()==null){
    		difficulties.setValue("Easy");
    	}
    	String x = difficulties.getValue();
    	//difficulties.getSelectionModel().selectFirst();
    	difficulties.setValue(x);
    	
    	difficulties.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
    		public void changed(ObservableValue ov, Number value, Number new_value){
    		gameController.setDifficulty(difficulties.getValue());
    		}
    	});
    	
    	System.out.println("Difficultie = "+gameController.getDifficulty());
    	//Labels
    	
    	scoreTxt.setText("Score:");
    	//scoreValue.setText(GameController.getScoreTxt());
    	lifePointsTxt.setText("HP:");
    	//lifePointsValue.setText(GameController.getLifePointsTxt());
    	
    	lifePointsValue.textProperty().bind(task.messageProperty());
    	Thread thread = new Thread(task);
    	thread.setDaemon(true);
        thread.start();
        
        scoreValue.textProperty().bind(scoreTask.messageProperty());
        Thread threadScore = new Thread(scoreTask);
        threadScore.setDaemon(true);
        threadScore.start();
    	
    	//Set items to toolbar  
    	toolbar.getItems().add(scoreBoardBtn);
    	toolbar.getItems().add(difficulties);
    	toolbar.getItems().add(startStopBtn);
    	
    	bToolBar.getItems().add(scoreTxt);
    	bToolBar.getItems().add(scoreValue);
    	bToolBar.getItems().add(lifePointsTxt);
    	bToolBar.getItems().add(lifePointsValue);
       // StackPane one = new StackPane();
       // one.getChildren().addAll(scoreValue);
    	//stack.getChildren().addAll(scoreTxt);
    	
    	//hbox.getChildren().addAll(stack,one);
    	//toolbar.getChildren().addAll(scoreBoardBtn,difficulties, startStopBtn, stack, one);
    	
    	root.setTop(toolbar);
    	root.setBottom(bToolBar);
    	//updateToolBar();
    	//root.set
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
    	        root.setTop(toolbar);
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
					GameController.endGame();
    	    	}
    	    }
    		});
		
	}
	/*
	public void difficultyListner(){
		difficulties.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
    		public void changed(ObservableValue ov, Number value, Number new_value){
    		gameController.setDifficulty(difficulties.getValue());
    		}
    	});
	}
	*/
	public void updateScoreValue(String score){
		final Task <Void> task = new Task<Void>(){
			@Override
			protected Void call() throws Exception {
				updateMessage(GameController.getLifePointsTxt());
				return null;
			}	
    	};
    	
    	Thread thread = new Thread(task);
    	thread.setDaemon(true);
        thread.start();
        
        lifePointsValue.textProperty().bind(task.messageProperty());
        //lifePointsValue.setText(GameController.getLifePointsTxt());
	}
	
	public void updateToolBar(){
	
    	
    	//lifePointsValue.textProperty().bind(task.messageProperty());
    	//Thread thread = new Thread(task);
    	//thread.setDaemon(true);
        //thread.start();
        scoreTxt.setText("Score:");
    	scoreValue.setText(GameController.getScoreTxt());
    	lifePointsTxt.setText("HP:");
    	lifePointsValue.setText(GameController.getLifePointsTxt());
       // scoreValue.textProperty().bind(scoreTask.messageProperty());
        //Thread threadScore = new Thread(scoreTask);
       // threadScore.setDaemon(true);
       // threadScore.start();
		
		scoreValue.setText(GameController.getScoreTxt());
		bToolBar.getItems().add(scoreTxt);
    	bToolBar.getItems().add(scoreValue);
    	bToolBar.getItems().add(lifePointsTxt);
    	bToolBar.getItems().add(lifePointsValue);
		//scoreValue.setText("This is a test");
		root.setBottom(bToolBar);
	}
	public void resetStartButton(){
		startStopBtn.setText("Start");
	}
	
	public int getallScoresSize(){
		return allScores.size();
	}
	
	//Get Methods
	public int getHighScore(){
		return this.highScore;
		}
	
	public List<Scores> getAllScores(){
		return allScores;
	}
	
	//Set Methods
	public void setScore(int score){
		this.score = score;
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
			allScores.add(new Scores(score, counter));
		}
		counter++;
	}
	
	//GridPane to display the scores in one column and difficulty in another
	public class CreateGridPane extends Parent{
		private BorderPane score;
		public CreateGridPane(BorderPane scoreBoard){
		this.score = scoreBoard;

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

		grid.add(scoreTitle,3,0); //column 2, row 1
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
		Text difficultyHeader = new Text("HighScore");
		difficultyHeader.setFont(Font.font("Arial",FontWeight.BOLD, 20));
		grid.add(difficultyHeader, 5, 1); //column 3, row 2
		//limited to last 20 scores shown
		
		for(int i=0;i<allScores.size();i++){
			//make this use list instead of temp
		/*	scoreTxt =new Text("Temp"+ i);
			grid.add(scoreTxt,1,i+2);
			difficultyTxt = new Text("Dif Temp"+i);
			grid.add(difficultyTxt,5,i+2);
			*/
			String Temp = allScores.get(i).toString();
			scoreTxt = new Text(Temp);
			scoreTxt.setFont(Font.font("Arial",FontWeight.SEMI_BOLD,16));
			grid.add(scoreTxt,1,i+2);
		}
		String hs = Integer.toString(getHighScore());
		Text highScore = new Text(hs);
		highScore.setFont(Font.font("Arial",FontWeight.SEMI_BOLD,16));
		grid.add(highScore,5,2);
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
