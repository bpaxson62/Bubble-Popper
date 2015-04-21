package edu.fgcu;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by brian on 3/12/2015.
 */
public class GifCreator {
    private File Gif;
    private String dir;
    public static boolean record = true;
    public static final String pictureStorageDir = System.getProperty("user.dir") + "//src//Main//tmp";
    public static final String gifOutputDir = System.getProperty("user.dir") + "//src/Main//gifs";

    public GifCreator() {
    }

    public static int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4;


    public static int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4;

    public void createImage(){

    }

    public static void startRecord() {
        record = true;
//        Platform.setImplicitExit(false);
        Thread recordThread = new Thread() {
            @Override
            public void run() {
                Robot rt;
                int cnt = 0;
                try {
                    rt = new Robot();
                    while (cnt == 0 || record) {
                        BufferedImage img = rt.createScreenCapture(new Rectangle(screenWidth, screenHeight));
//                        WritableImage image = Main.canvas.snapshot(null, null);
//                        BufferedImage img = SwingFXUtils.fromFXImage(image, null);
//                        ImageIO.write(bImage, format, file);
                        ImageIO.write(img, "jpeg", new File(pictureStorageDir + File.separator
                                + System.currentTimeMillis() + ".jpeg"));
                        if (cnt == 0) {

                            record = true;
                            cnt = 1;
                        }
                        Thread.sleep(50);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        };
//        Task<Integer> task = new Task<Integer>() {
//            @Override public Integer call() throws Exception {
//                Robot rt;
//                int cnt = 0;
//                try {
//                    rt = new Robot();
//                    while (cnt == 0 || record) {
////                        BufferedImage img = rt.createScreenCapture(new Rectangle(screenWidth, screenHeight));
//                        WritableImage image = Main.canvas.snapshot(null, null);
//                        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
////                        ImageIO.write(bImage, format, file);
//                        ImageIO.write(bImage, "jpeg", new File(pictureStorageDir + File.separator
//                                + System.currentTimeMillis() + ".jpeg"));
//                        if (cnt == 0) {
//
//                            record = true;
//                            cnt = 1;
//                        }
//                        Thread.sleep(50);
//                    }
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//
////            return 0;
//        };
//            new Thread(task).start();
        recordThread.start();
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

    private static void deleteDir() {
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

    private static boolean isPicture(String file) {
        String[] fileSplit = file.split("\\.");
        String extension = fileSplit[fileSplit.length - 1];

        for (String type : acceptedPictureTypes) {
            if (extension.equals(type)) return true;
        }

        return false;
    }
}
