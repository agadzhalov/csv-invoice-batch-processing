package com.invoice.processing.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(ImageDecoder.class);

    public static void decode(String base64Image, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            logger.error("[IMAGE_NOT_FOUND]" + e.getMessage());
        } catch (IOException e) {
            logger.error("[IMAGE_ERROR]" + e.getMessage());
        }
    }

}
