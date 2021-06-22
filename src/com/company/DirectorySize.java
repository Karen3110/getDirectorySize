package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DirectorySize extends Thread{
    private static  String stringPath;
    private static  List<File> files = new LinkedList<>();
    private static long finalSize;

    public DirectorySize(String path) {
        stringPath = path;
    }

    public static long getDirectorySize() {

        Path path = Path.of(stringPath);
        long size = 0;

        try (Stream<Path> walk = Files.walk(path)) {

            size = walk
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.out.println(p + " " + e);
                            return 0L;
                        }
                    })
                    .sum();

        } catch (IOException e) {
            System.out.println("IO errors: " + e);
        }

        return size;

    }

    public  static void getDirectorySizeRec(){
      findSize(Objects.requireNonNull(new File(stringPath).listFiles()));
    }
    private static void findSize(File[] files){
        for (File file:
             files) {
            if(file.isDirectory()){
                findSize(Objects.requireNonNull(file.listFiles()));
            }else if (file.isFile()){
                finalSize+=file.length();
                System.out.println(finalSize);
            }
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run(){
       getDirectorySizeRec();
    }
}
