package com.miplus.generaterasa.utils;

import java.io.File;

/**
 * 可以输出指定文件的路径
 */
public class DirectoryStructurePrinter {

    /**
     * 打印文件的结构
     * @param directory
     */
    public static void printDirectoryStructure(File directory) {
        printDirectoryStructure(directory, "");
    }

    private static void printDirectoryStructure(File directory, String prefix) {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            System.out.print(prefix + (i == files.length - 1 ? "└── " : "├── ") + file.getName() + "\n");
            if (file.isDirectory()) {
                printDirectoryStructure(file, prefix + (i == files.length - 1 ? "    " : "│   "));
            }
        }
    }
}
