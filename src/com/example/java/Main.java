package com.example.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


// @author Adam Brandt
public class Main {

    public static void main(String[] args) {
        ArrayList <String> contents = new ArrayList<>();
        ArrayList <String> newList = new ArrayList<>();
        ArrayList <String> dupList = new ArrayList<>();

        // PROD
        // String musicLocation = "E:\\Music";
        // TEST
        String musicLocation = "D:\\Project\\Test";

        Song.getDirInfo(musicLocation, contents);
        collectDups(contents, newList, dupList);

        Collections.sort(dupList);

        File file = newTempFile();
        writeUsingFiles(dupList, file);

    }

    public static File newTempFile() {
        File dupList = new File("duplicates.txt");
        if (dupList.exists()) {
            boolean del = dupList.delete();
            if (!del) {
                System.out.println("Could not delete previous version of " + dupList);
            }
        } else {
            try {
                boolean createNewFile = dupList.createNewFile();
                System.out.println("It is " + createNewFile + " that the new file was created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dupList;
    }

    public static <T> void collectDups(ArrayList<T> list, ArrayList<T> newList, ArrayList<T> dupList) {
        newList.add(list.get(0));
        for (T songs : list) {
            String[] s1 = songs.toString().split(Pattern.quote("\\"));
            String song = s1[s1.length - 1];
            String album = s1[s1.length -2];
            String artist = s1[s1.length - 3];

            for (int j = 0; j < newList.size(); j++) {
                String[] s2 = newList.get(j).toString().split(Pattern.quote("\\"));
                String song2 = s2[s2.length - 1];
                String album2 = s2[s2.length -2];
                String artist2 = s2[s2.length - 3];

                if (song.equalsIgnoreCase(song2)
                        && artist.equalsIgnoreCase(artist2)
                        && !album.equalsIgnoreCase(album2)) {
                    dupList.add(songs);
                    dupList.add(newList.get(j));
                    break;
                } else if (song.equalsIgnoreCase(song2)
                        && !artist.equalsIgnoreCase(artist2)
                        && album.equalsIgnoreCase(album2)) {
                    dupList.add(songs);
                    dupList.add(newList.get(j));
                    break;
                } else if (songs.equals(newList.get(j))) {
                    break;
                } else if (j == newList.size() - 1){
                    newList.add(songs);
                    break;
                }
            }
        }
    }

    private static void writeUsingFiles(List <String> data, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Object datum : data) {
                printWriter.println(datum);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


