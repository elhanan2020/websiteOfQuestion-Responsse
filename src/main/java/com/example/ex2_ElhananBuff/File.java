package com.example.ex2_ElhananBuff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File {

    private final String myPath ;

    private ArrayList<String> myQuestion = new ArrayList<String>();

    public File(String path){
        myPath = path;
    }


    public ArrayList<String>  getQuestion()throws Exception {
         String line = "";

        try( BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\owner\\IdeaProjects\\ex2_ElhananBuff\\src\\main\\questions.txt")))
        {
            System.out.println(line);
            int i= 0;
            for ( ; (line = reader.readLine()) != null  ; i++){
                       myQuestion.add(line);
            }
          }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return myQuestion;
    }
}



