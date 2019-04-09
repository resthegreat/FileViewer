package me.resmagnus.fileexplorer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileExplorer {

    private ArrayList<File> directoryStack;
    private File[] currentDirectoryFiles;

    public FileExplorer() {

        directoryStack = new ArrayList<>();

        if(OSValidator.isWindows()) {
            addDirectory(new File(System.getenv("SystemDrive") + "//"));
        }

        else if(OSValidator.isUnix()) {
            addDirectory(new File("/"));
        }

        currentDirectoryFiles = currentDirectoryFilesToArray();
    }

    public void printCurrentDirectoryFiles() {
        for(int i = 0; i < currentDirectoryFiles.length; i++) {
            System.out.println(i + " " + currentDirectoryFiles[i].getName());
        }
        System.out.println("\n");
    }

    public void addDirectory(File dir) {
        if(dir.isDirectory()) {
            directoryStack.add(dir);
            currentDirectoryFiles = currentDirectoryFilesToArray();
        }
        else {
            System.out.println("Error: Selected file is not a directory.\n");
        }
    }

    public void removeLastDirectory() {
        if(directoryStack.size() != 1) {
            directoryStack.remove(directoryStack.get(directoryStack.size() - 1));
        }
    }

    public File[] currentDirectoryFilesToArray() {
        File[] s = directoryStack.get(directoryStack.size() - 1).listFiles();
        if(s != null) {
            return s;
        }

        else {
            removeLastDirectory();
            System.out.println("Error: You do not have permission to view the contents of this folder.\n");
            return s = directoryStack.get(directoryStack.size() - 1).listFiles();
        }
    }

    public File[] getCurrentDirectoryFiles() {
        return currentDirectoryFiles;
    }

    public void resetCurrentDirectoryFiles() {
        currentDirectoryFiles = currentDirectoryFilesToArray();
    }

    public void openFile(File file) {
        if(file.isFile()) {
            try {
                Desktop.getDesktop().open(file);
            }catch(IOException e) {
                System.out.println("Error: Could not open file.\n");
            }
        }
        else {
            System.out.println("Error: Selected file is not a file.\n");
        }
    }
}
