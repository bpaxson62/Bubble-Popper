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
		GifCreator.makeDirectories();
		//test if gif file is created
		GifCreator.deleteDir();
		File input = new File(GifCreator.gifOutputDir);
		int sizeBefore = input.listFiles().length;
		GifCreator.deleteDir();
		GifCreator.startRecord();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GifCreator.stopRecording();
		File input2 = new File(GifCreator.gifOutputDir);
		int sizeAfter = input2.listFiles().length;
		assertEquals("Size indicates file creation",sizeAfter-1, sizeBefore);
		GifCreator.deleteDir();
	}
	
	@Test
	public void testGifSaveLocation(){
		GifCreator.makeDirectories();
		//test to make sure gif saves to correct location
		File input = new File(GifCreator.gifOutputDir);
		int sizeBefore = input.listFiles().length;
		GifCreator.deleteDir();
		GifCreator.startRecord();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GifCreator.stopRecording();
		File input2 = new File(GifCreator.gifOutputDir);
		int sizeAfter = input2.listFiles().length;
		assertEquals("Make sure location by file list",sizeAfter-1, sizeBefore);
		GifCreator.deleteDir();
	}
	
	@Test
	public void testGifSize(){
		GifCreator.makeDirectories();
		//test to make sure there is something in the gif file
		//gif should not be 0bits in size
		GifCreator.deleteDir();
		File input = new File(GifCreator.gifOutputDir);
		GifCreator.startRecord();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GifCreator.stopRecording();
		boolean findSize = false;
		for(int i = 0; i< input.listFiles().length; i++){
			GifCreator.isPicture(input.listFiles()[i].getName());
			if(input.listFiles()[i].length() > 0){
				findSize = true;
			}
		}
		assertTrue(findSize);
		GifCreator.deleteDir();
	}
}
