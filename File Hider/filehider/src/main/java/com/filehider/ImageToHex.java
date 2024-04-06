package com.filehider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageToHex {

    public ImageToHex(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java ImageToHex <image_file>");
            return;
        }

        String filePath = args[0];
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        StringBuilder hexString = new StringBuilder();
        for (byte b : fileBytes) {
            hexString.append(String.format("%02X", b & 0xFF));
        }

        System.out.println("Hex code of " + filePath + ":");
        System.out.println(hexString);
    }
}
