package edu.fgcu;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GifCreateTest {
	
	@Before
	public void setup(){
		//put setup info here

	}
	
	@Test
	public void testGifCreate(){
		//test if gif file is created
		File input = new File(GifCreator.gifOutputDir);
		int sizeBefore = input.listFiles().length;

		GifCreator.startRecord();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GifCreator.stopRecording();
		File input2 = new File(GifCreator.gifOutputDir);
		int sizeAfter = input2.listFiles().length;
		assertEquals("Size indicates file creation",sizeAfter-1, sizeBefore);
	}
	
	@Test
	public void testGifSaveLocation(){
		//test to make sure gif saves to correct location
		File input = new File(GifCreator.gifOutputDir);
		int sizeBefore = input.listFiles().length;

		GifCreator.startRecord();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GifCreator.stopRecording();
		File input2 = new File(GifCreator.gifOutputDir);
		int sizeAfter = input2.listFiles().length;
		assertEquals("Make sure location by file list",sizeAfter-1, sizeBefore);

	}
	
	@Test
	public void testGifSize(){
		//test to make sure there is something in the gif file
		//gif should not be 0bits in size
		File input = new File(GifCreator.gifOutputDir);
		GifCreator.startRecord();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GifCreator.stopRecording();
		for(int i = 0; i< input.listFiles().length; i++){
			System.out.println(input.listFiles()[i].length());
			assertTrue(input.listFiles()[i].length() > 0);
		}
	}

}
