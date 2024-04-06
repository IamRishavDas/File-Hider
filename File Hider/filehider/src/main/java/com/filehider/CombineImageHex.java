package com.filehider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CombineImageHex {

    public CombineImageHex(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.out.println("Usage: java CombineImageHex <image_file1> <image_file2>");
            return;
        }

        String filePath1 = args[0];
        String filePath2 = args[1];

        
        byte[] file1Bytes = Files.readAllBytes(Paths.get(filePath1));
        byte[] file2Bytes = Files.readAllBytes(Paths.get(filePath2));

       
        byte[] combinedBytes = combineArrays(file1Bytes, file2Bytes);

       
        String combinedHexString = bytesToHex(combinedBytes);

        System.out.println("Combined hex code:");
        System.out.println(combinedHexString); 

        System.out.println("Combined byte array:"); 
        System.out.println(combinedBytes);
    }

    private static byte[] combineArrays(byte[] arr1, byte[] arr2) {
        byte[] combined = new byte[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, combined, 0, arr1.length);
        System.arraycopy(arr2, 0, combined, arr1.length, arr2.length);
        return combined;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b & 0xFF));
        }
        return hexString.toString();
    }
}

