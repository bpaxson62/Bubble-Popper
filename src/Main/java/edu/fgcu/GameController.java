package edu.fgcu;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

public class GameController{
	
	private BorderPane root; //Instance of scene game node
	private int lifePoints;  //Users life points
	private int score; //Users Score
	private int difficulty;
	private Level level;
	
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
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	
	public void decreaseLifePoints(){
		lifePoints--;
		
		//If LifePOints hit zero, game is over
		if(lifePoints==0){
			endGame();
		}
	}
	
	public void gameStart(){
		lifePoints=10;
		score = 0;
		difficulty = 1; //Need to make this set by user with default of 1(easy)
		changeState(1);
	}
	
	


	public void increaseScore(){
		score++;
	}

	public void endGame(){
		root.setCenter(null);
	}

	public void changeState(int i) {
		Group myGroup = new Group();
		root.setCenter(new Level(0));


		//root.setCenter(myGroup);

		//border.setCenter();
	}
	
	

}
