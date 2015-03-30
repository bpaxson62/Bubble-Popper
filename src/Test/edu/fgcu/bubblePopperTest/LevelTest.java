package bubblePopperTest;

import org.junit.Before;
import org.junit.Test;


public class LevelTest {
	
	@Before
	public void setup() {
			//put setup info here

	}
	
	@Test
	public void testBubbleCreate() {
		// Test for creating a bubble
		//Should create bubble of radius=RADIUS at coordinates supplied

	}
	
	@Test
	public void testBubbleMinBoundries() {
		//Test for minimum distance from walls and other bubbles
		//before creating bubble at locations
		throw new RuntimeException();
		
	}
	
	@Test
	public void testBubbleClicked() {
		//Test for bubble when user clicks it
		//Bubble should pop and user be awarded points
		throw new RuntimeException();
	}
	
	@Test
	public void testBubbleHitsWall() {
		//Test for when bubble hits a wall
		//Bubbble should pop and user loses life
		throw new RuntimeException();
	}
	
	@Test
	public void testBubbleHitsBubble() {
		//Test for when bubbble hits another bubble
		//Both bubbles should pop and user loses life twice
		throw new RuntimeException();
	}
	
	@Test
	public void testRandom() {
		//Test random points creation
		//Should not receive same points over and over again
		throw new RuntimeException();
	}
}
