package edu.fgcu;

import javafx.scene.layout.BorderPane;

public class GameController{
	
	private static BorderPane root; //Instance of scene game node
	private static int lifePoints;  //Users life points
	private static String lifePointsTxt;
	private static int score; //Users Score
	private static String scoreTxt;
	private static int difficulty=0;
	private static String difficultyTxt="Easy";
	public static Level myLevel;
	private static ScoreBoardController scoreBoardController;
	
	public GameController(BorderPane game){
		this.root = game;
		this.myLevel = new Level(0);
		root.setCenter(myLevel);
	}
	
	public static ScoreBoardController getScoreBoardController(){
    	return scoreBoardController;
    }
	
	public static void setScore(int score) {
        GameController.score = score;
        setScoreTxt(GameController.score);
        //scoreBoardController = new ScoreBoardController(root);
		//scoreBoardController.createToolBar(1,getDifficultyTxt());
    }
	
	public static void setScoreTxt(int score){
		String scrTxt = Integer.toString(score);
		GameController.scoreTxt = scrTxt;
		scoreBoardController = new ScoreBoardController(root);
		//scoreBoardController.createToolBar(1);
		scoreBoardController.updateScoreValue(scoreTxt);
	}
	
	public static String getScoreTxt(){
		if (scoreTxt==null){
			setScoreTxt(0);
		}
		return scoreTxt;
	}
	
	public int getScore(){
		return score;
	}
	
	public static int getLifePoints(){
		return lifePoints;
	}
	
	public static void setLifePointsTxt(int lifePoints){
		String lPTxt = Integer.toString(lifePoints);
		GameController.lifePointsTxt = lPTxt;
		scoreBoardController = new ScoreBoardController(root);
		//scoreBoardController.createToolBar(1);
		
	}
	
	public static String getLifePointsTxt(){
		if (lifePointsTxt==null){
			setLifePointsTxt(10);
		}
		return lifePointsTxt;
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public static String getDifficultyTxt(){
		return difficultyTxt;
	}
	
	public void setDifficulty(String dif){
		int x = 0;
		if(dif == "Easy"){x=1;}
		if(dif=="Normal"){x=2;}
		if(dif=="Hard"){x=3;}
		
		this.difficulty = x;
	}
	
	public static void decreaseLifePoints(){
		lifePoints--;
		setLifePointsTxt(lifePoints);
		//lifePointsTxt = Integer.toString(lifePoints);
		//If LifePOints hit zero, game is over
		if(lifePoints==0){
			endGame();
		}
	}
	
	
	public void gameStart(){
		lifePoints=10;
		setLifePointsTxt(lifePoints);
		score = 0;
		setScoreTxt(score);
		difficulty = getDifficulty(); //Need to make this set by user with default of 1(easy)
		//changeState(1);
		System.out.println("game start was run");
		System.out.println("lifepoints = "+getLifePointsTxt());
		scoreBoardController = new ScoreBoardController(root);
		System.out.println("Difficulty is: "+getDifficulty());
		//scoreBoardController.createToolBar(1);
	}
	
	


	public static void increaseScore(){
		score++;
		setScoreTxt(score);
	}

	public static void endGame(){
		//root.setCenter(null);
		myLevel.stopGame();
		scoreBoardController.addScoreToList(GameController.score, GameController.difficulty);
	}

	public void changeState(int i) {
//		Group myGroup = new Group();
		if(i == 0){
			myLevel.stopGame();
			
		}else{
			myLevel.startGame();
			gameStart();
		}
		//root.setCenter(myGroup);
		//border.setCenter();
	}
	
	

}
