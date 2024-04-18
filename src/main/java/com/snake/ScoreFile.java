package com.snake;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ScoreFile {
    public static int score = 0;
    public static int highest_score;
    public static ArrayList<Integer> numbers = new ArrayList<>(5);
    private static final String recordsFileName = "src/main/resources/filename.txt";
    // private InputStream inputStream = ScoreFile.class.getClassLoader().getResourceAsStream(recordsFileName);

    public ScoreFile() {

    }

    public void write_a_file(int score) {
        // If the score is higher than records, record new score
        try{
            // BufferedWriter writer = new BufferedWriter(new FileWriter(recordsFileName));
            FileWriter writer = new FileWriter(recordsFileName);
            if (score > highest_score){
                writer.write("The highest scroes:" + score+ "\n");
                highest_score = score;
        // Keep the original score
            } else {
                writer.write("The highest scroes:" + highest_score+ "\n");
            }
            writer.write("the score of this game:" + score+ "\n");
            if(score>numbers.get(0)){
                numbers.set(0, score);
                Collections.sort(numbers);
            }
            writer.write("Top five scores:"+ numbers.get(4)+" "+numbers.get(3)+" "+numbers.get(2)+" "+numbers.get(1)+" "+numbers.get(0));
            
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void read_highest_score() {
        // If the file already exist, read the highest score
        try{
            File myObj = new File(recordsFileName);
            Scanner sc = new Scanner(myObj);
            String highest_score_line = sc.nextLine();
            String[] highest_score_parts = highest_score_line.split(":");
                highest_score = Integer.parseInt(highest_score_parts[1].trim());

            String score_line = sc.nextLine();
            String[] score_parts = score_line.split(":");
            int score_record = Integer.parseInt(score_parts[1].trim());

            String score_rank_line = sc.nextLine();
            String[] rank_numbers = score_rank_line.split(":")[1].trim().split("\\s+");
            for (String num : rank_numbers) {
                numbers.add(Integer.parseInt(num));
            }
            Collections.sort(numbers);

            sc.close();
        // If File is not exist, highest score = 0, open new file and save the records
        } catch (FileNotFoundException e){
            highest_score = 0;
            try{
                File myObj = new File(recordsFileName);
                if (myObj.createNewFile()){
                    System.out.println("File creater: " + myObj.getName());
                }
                FileWriter fw = new FileWriter(myObj.getName());
                fw.write("" + 0);
                fw.close();
            } catch (IOException err) {
                System.out.println("An error occures");
                err.printStackTrace();
            }
        }
    }
}
