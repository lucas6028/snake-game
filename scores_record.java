package Java軟體實務.Snake;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class scores_record {

    static int highest_score;

    public void main(String[] args){

    }
    public void read_highest_scores(){
        //檔案已經存在，讀出最高分成績
        try{
            File myObj = new File("filename.txt");
            Scanner sc = new Scanner(myObj);
            highest_score = sc.nextInt();
            sc.close();
        //檔案不存在，最高分=0，開新檔案儲存紀錄
        } catch (FileNotFoundException e){
            highest_score = 0;
            try{
                File myObj = new File("filename.txt");
                if (myObj.createNewFile()){
                    System.out.println("File creater: " + myObj.getName());
                }
                FileWriter fw = new FileWriter(myObj.getName());
                fw.write(""+0);
            } catch (IOException err) {
                System.out.println("An error occures");
                err.printStackTrace();
        }
    }
}
    public void write_a_file(int score){
        //比原有記錄高，紀錄新分數
        try{
            FileWriter fw = new FileWriter("filename.txt");
            if (score > highest_score){
                fw.write("" + score);
                highest_score = score;
        //保留原有最高分
            } else {
                fw.write("" + highest_score);
            }
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
