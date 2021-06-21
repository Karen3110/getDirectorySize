package com.company;

import com.company.service.DirectorySize;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {



    public static void main(String[] args) {


        long directorySize = DirectorySize.getDirectorySize(new File("").getAbsolutePath() + "\\src\\testFolder");
        System.out.println(directorySize);


    }
}
