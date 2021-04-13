package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Message;

import java.io.File;
import java.util.ArrayList;

public class M {

    public static void main(String[] args) {

        ArrayList<String> results = new ArrayList();
        File[] files = new File("LibraryOfOutput").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }

        }
        System.out.println(results);
    }
}

