package com.example.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Song {

    private String dir;
    private ArrayList<String> al;

    public Song(String dir) {
        this.dir = dir;

    }

    public static void getDirInfo(String dir, ArrayList<String> al) {
        File folder = new File(dir);
        File[] content;
        String mp3 = ".mp3";

        try {
            content = folder.listFiles();
            for (File song : Objects.requireNonNull(content)) {
                if ((song.isFile()) && song.getName().endsWith(mp3)) {
                    al.add(song.toString());
                } else if (song.isDirectory()) {
                    getDirInfo(song.toString(), al);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
