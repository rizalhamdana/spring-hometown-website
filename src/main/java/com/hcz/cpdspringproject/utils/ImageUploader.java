package com.hcz.cpdspringproject.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageUploader {

    public static String uploadImage(CommonsMultipartFile file, HttpSession session) throws IOException {
        ServletContext context = session.getServletContext();
        String path = context.getRealPath("/uploads/");
        String filename = file.getOriginalFilename();

        System.out.println(path + " " + filename);

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(new File(path + File.separator + filename)));
        stream.write(bytes);
        stream.flush();
        stream.close();
        return filename;
    }

    public static boolean isSettedImage(Part part) {
        String content = part.getHeader("content-disposition");
        String fileaddress = content.substring(content.lastIndexOf("=") + 2, content.length() - 1);
        if (fileaddress.equals(null) || fileaddress.length() <= 0 || fileaddress.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static String uploadImage(List<Part> parts, HttpServlet servlet) {
        String thumbnails = "";

        for (Part part : parts) {
            String content = part.getHeader("content-disposition");
            System.out.println(part.getName());
            if (part.getName().equalsIgnoreCase("thumbnail")) {
                String fileaddress = content.substring(content.lastIndexOf("=") + 2, content.length() - 1);
                String filename = fileaddress.substring(fileaddress.lastIndexOf("\\") + 1, fileaddress.length());
                String saveFile = servlet.getServletContext().getRealPath("/uploads/");
                File cover = new File(saveFile);
                try {
                    part.write(cover + "/" + filename);
                } catch (IOException e) {

                    e.printStackTrace();
                }
                thumbnails = thumbnails + filename + ", ";
            }
        }

        return thumbnails;

    }

}
