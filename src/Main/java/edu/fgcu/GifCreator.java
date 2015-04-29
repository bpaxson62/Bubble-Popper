package edu.fgcu;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Created by brian on 3/12/2015.
 */
public class GifCreator {
    public static boolean record = true;
    public static final String pictureStorageDir = System.getProperty("user.dir") + "//src//Main//tmp";
    public static final String gifOutputDir = System.getProperty("user.dir") + "//src/Main//gifs";
    public static int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static void startRecord() {
        record = true;
        makeDirectories();

        Thread recordThread = new Thread() {
            @Override
            public void run() {
                Robot rt;

                int cnt = 0;
                try {
                    rt = new Robot();
                    while (cnt == 0 || record) {


                        BufferedImage image = rt.createScreenCapture(new Rectangle(screenWidth, screenHeight));
                        File compressedImageFile = new File(pictureStorageDir + File.separator
                                + System.currentTimeMillis() + ".jpg");


                        OutputStream os =new FileOutputStream(compressedImageFile);

                        Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName("jpg");
                        ImageWriter writer = (ImageWriter) writers.next();

                        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
                        writer.setOutput(ios);

                        ImageWriteParam param = writer.getDefaultWriteParam();

                        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                        param.setCompressionQuality(0.5f);
                        writer.write(null, new IIOImage(image, null, null), param);
                        ImageIO.write(image, "jpg", new File(pictureStorageDir + File.separator
                                + System.currentTimeMillis() + ".jpg"));
                        if (cnt == 0) {
                            record = true;
                            cnt = 1;
                        }
                        Thread.sleep(25);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        };


        recordThread.start();
    }

    public static void  makeDirectories(){
        File file = new File(pictureStorageDir);
        if(!file.exists()){
            file.mkdir();
        }
        File file2 = new File(gifOutputDir);
        if(!file2.exists()){
            file2.mkdir();
        }
    }

    public static void stopRecording() {
        record = false;

        File input = new File(pictureStorageDir);

        if (!input.exists()) {
            return;
        }

        File[] fileLst = input.listFiles();

        if (fileLst == null || fileLst.length == 0) {
            return;
        }

        try {

            BufferedImage firstImage = ImageIO.read(fileLst[0]);
            // create a new BufferedOutputStream with the last argument

            ImageOutputStream output =
                    new FileImageOutputStream(new File(gifOutputDir + File.separator + System.currentTimeMillis() + ".gif"));

            // create a gif sequence with the type of the first image, 1 second
            // between frames, which loops continuously
            GifSequenceWriter writer =
                    new GifSequenceWriter(output, firstImage.getType(), 1, false);

            // write out the first image to our sequence...
            writer.writeToSequence(firstImage);
            for (int i = 1; i < fileLst.length - 1; i++) {
                if (isPicture(fileLst[i].getName())) {
                    BufferedImage nextImage = ImageIO.read(fileLst[i]);
                    writer.writeToSequence(nextImage);
                }
            }
            writer.close();
            output.close();
        } catch (Exception ignored) {
        } finally {
            deleteDir();
        }

    }

    public static void deleteDir() {
        File input = new File(pictureStorageDir);

        if (!input.exists()) {
            return;
        }
        File[] files = input.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            file.delete();
        }
    }

    private static final String[] acceptedPictureTypes = {"jpg", "png", "jpeg"};

    public static boolean isPicture(String file) {
        String[] fileSplit = file.split("\\.");
        String extension = fileSplit[fileSplit.length - 1];

        for (String type : acceptedPictureTypes) {
            if (extension.equals(type)) return true;
        }
        return false;
    }
}
