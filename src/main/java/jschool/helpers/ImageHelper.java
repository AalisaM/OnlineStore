package jschool.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

/**
 * Helper used to load images in application
 * */
public class ImageHelper {


    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(100, 100, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 100, 100, null);
        g.dispose();

        return resizedImage;
    }

    /**
     * Gets file from params, processes it, resizes, saves and returns its source.
     * */
    public static String uploadFileHandler(MultipartFile file, String productFileName) {
      //  System.out.println(Paths.get(imagesFolder));
        if (!file.isEmpty()) {
            try {
                String rootPath = "B:\\store";

                File dir = new File(rootPath);

                if (!dir.exists()){
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + productFileName + ".png");

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));

                stream.write(file.getBytes());
                stream.close();

                BufferedImage originalImage = ImageIO.read(serverFile);
                int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                ImageIO.write(resizeImageJpg, "png", new File(dir.getAbsolutePath()
                        + File.separator + productFileName + "_icon.png"));


                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());

                System.out.println("You successfully uploaded file=" + serverFile.getAbsolutePath());
                return serverFile.getAbsolutePath();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + file.getOriginalFilename()
                    + " because the file was empty.");
        }
        return "";
    }
}
