package com.example.rent.accoutputreadingappwithfragments;

import android.os.Environment;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-02-20.
 */

public class FileManager {
    public static final FileManager instance = new FileManager();
    private static final String FOLDER_NAME = "accelometer_outputs";

    private DataInputStream binReader;


    private void openInputStreamReader(String filePath) {

        try {
            binReader = new DataInputStream(new FileInputStream(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<File> getFilesFromFolder() {
        List<File> list = new ArrayList<>();

        File containingFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + FOLDER_NAME);
        if (!containingFolder.exists()) {
            containingFolder.mkdir();
        }
        for (File file : containingFolder.listFiles()) {
            if (file.isFile()) list.add(file);
        }

        return list;
    }

    public String readBinFile(String filePath) throws IOException {
        openInputStreamReader(filePath);
        StringBuilder builder = new StringBuilder();

        while (binReader.available() >= 12) {

            builder.append(String.valueOf(binReader.readFloat()));
            builder.append(", ");
            builder.append(String.valueOf(binReader.readFloat()));
            builder.append(", ");
            builder.append(String.valueOf(binReader.readFloat()));
            builder.append("\n");
        }

        binReader.close();
        return builder.toString();
    }
}
