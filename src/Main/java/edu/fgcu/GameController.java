package edu.fgcu;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

public class GameController{
	
	private BorderPane root; //Instance of scene game node
	private int lifePoints;  //Users life points
	private static String lifePointsTxt;
	private int score; //Users Score
	private int difficulty;
	public static Level mylevel;
	
	public GameController(BorderPane game){
		this.root = game;
	}
	
	
	public void setScore(int score) {
        this.score = score;
    }
	
	public int getScore(){
		return score;
	}
	
	public int getLifePoints(){
		return lifePoints;
	}
	
	public static String getLifePointsTxt(){
		return lifePointsTxt;
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	
	public void decreaseLifePoints(){
		lifePoints--;
		lifePointsTxt = Integer.toString(lifePoints);
		//If LifePOints hit zero, game is over
		if(lifePoints==0){
			endGame();
		}
	}
	
	public void gameStart(){
		lifePoints=10;
		lifePointsTxt=Integer.toString(lifePoints);
		score = 0;
		difficulty = 1; //Need to make this set by user with default of 1(easy)
		//changeState(1);
	}
	
	


	public void increaseScore(){
		score++;
	}

	public void endGame(){
		root.setCenter(null);
	}

	public void changeState(int i) {
		if(mylevel != null){
			mylevel = new Level(0);
		}
//		Group myGroup = new Group();
		root.setCenter(mylevel);
		//root.setCenter(myGroup);

		//border.setCenter();
	}
	
	

}
