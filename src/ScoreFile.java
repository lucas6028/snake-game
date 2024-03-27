import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;


public class ScoreFile {
    public static int score = 0;
    public static int highest_score;
    
    private static final String recordsFileName = "../records/filename.txt";

    public ScoreFile() {

    }

    public void write_a_file(int score) {
        // If the score is higher than records, record new score
        try{
            FileWriter fw = new FileWriter(recordsFileName);
            if (score > highest_score){
                fw.write("" + score);
                highest_score = score;
        // Keep the original score
            } else {
                fw.write("" + highest_score);
            }
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void read_hightes_score() {
        // If the file already exist, read the highest score
        try{
            File myObj = new File(recordsFileName);
            Scanner sc = new Scanner(myObj);
            highest_score = sc.nextInt();
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
