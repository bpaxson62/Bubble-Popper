package bubblePopper;


import javafx.animation.ScaleTransition;
import javafx.application.Application;
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
    	/* This creates the basic window, commented out for the moment
    	 * This works, must remove init(primaryStage) to make it work
    	Group game = new Group();
    	gameController = new GameController(game);
    	primaryStage.setTitle("Bubble Popper");
    	primaryStage.setResizable(false);
    	primaryStage.setWidth(Configurations.MAIN_SCREEN_WIDTH + 2*Configurations.WINDOW_BOARDER);
    	primaryStage.setHeight(Configurations.MAIN_SCREEN_HEIGHT + 2*Configurations.WINDOW_BOARDER);
    	Scene scene = new Scene(game);
    	primaryStage.setScene(scene);
    	scene.setFill(Color.BLACK);
    	*/
//        init(primaryStage);
//        primaryStage.show();
//        play();
    }
    
    public static void main(String[] args) { launch(args); }



}
