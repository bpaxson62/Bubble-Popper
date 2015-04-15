package edu.fgcu;
public final class Configurations  {
	
	//Window Sizes
	public static final int MAIN_SCREEN_WIDTH=700;
	public static final int MAIN_SCREEN_HEIGHT=700;
	public static final int SCORE_SCREEN_WIDTH=500;
	public static final int SCORE_SCREEN_HEIGHT=700;
	public static final int WINDOW_BOARDER=3;
	public static final int TITLE_BAR_HEIGHT=0;
	
	//Difficulty settings
	public static final int LIFE_POINTS=0;
	public static final int BUBBLE_STARTING_RADIUS=0;
	
	
	//Difficulty Names
	public static final String EASY_DIFFICULTY="Easy";
	public static final String NORMAL_DIFFICULTY="Normal";
	public static final String HARD_DIFFICULTY="Hard";

	//Level Config
	public static final int radius = 25;

	//css
	public static final String circleStyle = "-fx-fill: \n" +
			"        linear-gradient(#ffd65b, #e68400),\n" +
			"        linear-gradient(#ffef84, #f2ba44),\n" +
			"        linear-gradient(#ffea6a, #efaa22),\n" +
			"        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
			"        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
			"    -fx-background-radius: 30;\n" +
			"    -fx-background-insets: 0,1,2,3,0;\n" +
			"    -fx-text-fill: #654b00;\n" +
			"    -fx-font-weight: bold;\n" +
			"    -fx-font-size: 14px;\n" +
			"    -fx-padding: 10 20 10 20;";

}
