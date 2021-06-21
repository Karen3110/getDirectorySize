package com.company.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DirectorySize {
    public static long getDirectorySize(String stringPath) {

        Path path = Path.of(stringPath);
        long size = 0;

        try (Stream<Path> walk = Files.walk(path)) {

            size = walk
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.out.println(p+" "+e);
                            return 0L;
                        }
                    })
                    .sum();

        } catch (IOException e) {
            System.out.println("IO errors: " +e);
        }

        return size;

    }
}
