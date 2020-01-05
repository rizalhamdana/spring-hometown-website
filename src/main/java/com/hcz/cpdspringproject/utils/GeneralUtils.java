package com.hcz.cpdspringproject.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * GeneralUtils
 */
public class GeneralUtils {

    private static ApplicationContext APP_CONTEXT = new ClassPathXmlApplicationContext("beans.xml");

    public static ApplicationContext getContext() {
        if (APP_CONTEXT == null) {
            APP_CONTEXT = new ClassPathXmlApplicationContext("beans.xml");

        }
        return APP_CONTEXT;
    }

    public static String handleFileUpload(MultipartFile file, String uploadPath) {
        String filename = null;
        if (!file.isEmpty()) {
            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();

                Path path = Paths.get(uploadPath + file.getOriginalFilename());
                Files.write(path, bytes);

                filename = file.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filename;
    }
}