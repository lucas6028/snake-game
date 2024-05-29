package com.snake;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;

public class ScoreFile {
    public static int score = 0;
    public static int highest_score;
    public static ArrayList<Integer> numbers = new ArrayList<>(5);
    private static final String recordsFileName = "filename.txt";

    public ScoreFile() {
        
    }

    public void write_a_file(int score) {
        try {
            File file = getFileFromResources(recordsFileName);
            FileWriter writer = new FileWriter(file);
            if (score > highest_score) {
                writer.write("The highest scores:" + score + "\n");
                highest_score = score;
            } else {
                writer.write("The highest scores:" + highest_score + "\n");
            }
            writer.write("the score of this game:" + score + "\n");
            if (score > numbers.get(0)) {
                numbers.set(0, score);
                Collections.sort(numbers);
            }
            writer.write("Top five scores:" + numbers.get(4) + " " + numbers.get(3) + " " + numbers.get(2) + " " + numbers.get(1) + " " + numbers.get(0));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read_highest_score() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(recordsFileName);
            if (inputStream != null) {
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);
                String highest_score_line = reader.readLine();
                String[] highest_score_parts = highest_score_line.split(":");
                highest_score = Integer.parseInt(highest_score_parts[1].trim());

                String score_line = reader.readLine();
                String[] score_parts = score_line.split(":");
                int score_record = Integer.parseInt(score_parts[1].trim());

                String score_rank_line = reader.readLine();
                String[] rank_numbers = score_rank_line.split(":")[1].trim().split("\\s+");
                for (String num : rank_numbers) {
                    numbers.add(Integer.parseInt(num));
                }
                Collections.sort(numbers);

                reader.close();
            } else {
                highest_score = 0;
                // Do something if file doesn't exist
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        } else {
            return new File(resource.getFile());
        }
    }
}
