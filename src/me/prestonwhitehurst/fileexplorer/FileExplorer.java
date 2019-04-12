package me.prestonwhitehurst.fileexplorer;

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
            setCurrentDirectory(new File(System.getenv("SystemDrive") + "//"));
        }

        else if(OSValidator.isUnix()) {
            setCurrentDirectory(new File("/"));
        }
    }

    public void setCurrentDirectory(File dir) {
        if(dir.isDirectory()) {
            directoryStack.add(dir);
            setCurrentDirectoryFiles();

            if(currentDirectoryFiles == null) {
                System.out.println("Error: You do not have permission to view the contents of this folder.\n");
                removeCurrentDirectory();
            }
        }

        else {
            System.out.println("Error: Selected file is not a directory.\n");
        }
    }

    public void removeCurrentDirectory() {
        if(directoryStack.size() != 1) {
            directoryStack.remove(directoryStack.get(directoryStack.size() - 1));
            setCurrentDirectoryFiles();
        }
    }

    public File getCurrentDirectory() {
        return directoryStack.get(directoryStack.size() - 1);
    }

    public void setCurrentDirectoryFiles() {
        currentDirectoryFiles = directoryStack.get(directoryStack.size() - 1).listFiles();;
    }

    public File[] getCurrentDirectoryFiles() {
        return currentDirectoryFiles;
    }

    public void printCurrentDirectoryFiles() {
        System.out.println("Current directory: " + getCurrentDirectory().getAbsolutePath());
        for(int i = 0; i < currentDirectoryFiles.length; i++) {
            System.out.println(i + " " + currentDirectoryFiles[i].getName());
        }
        System.out.println("\n");
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
