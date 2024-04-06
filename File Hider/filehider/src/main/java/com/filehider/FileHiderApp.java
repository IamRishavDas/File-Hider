package com.filehider;

import java.io.IOException;

import java.io.*;

public class FileHiderApp {

    public static void main(String[] args) throws IOException {

        String file1Path   = System.console().readLine("Enter the FQDN of the raw image file: ");
        String file2Path   = System.console().readLine("Enter the FQDN of the zip file: ");
        String newFilePath = System.console().readLine("Enter the FQDN of new image file: ");

        FileInputStream fileInputStream1 = new FileInputStream(file1Path);
        FileInputStream fileInputStream2 = new FileInputStream(file2Path);

        FileOutputStream fileOutputStream = new FileOutputStream(newFilePath);

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fileInputStream1.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        while ((bytesRead = fileInputStream2.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream1.close();
        fileInputStream2.close();
        fileOutputStream.close();

        System.out.println("Files combined successfully!");
    }
}
