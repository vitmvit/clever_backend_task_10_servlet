package org.example.dao.util;

import java.io.File;

public class FileUtil {

    public static void deleteFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFilesInDirectory(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                }
            }
        }
    }
}
