
package com.example.ex2_ElhananBuff;

import java.util.ArrayList;

public class DataStructures {
    private ArrayList<String> myQuestion;
    private final ArrayList<String>[] myAnswer;
    private final ArrayList<String>[] authorOfAnswer;


    public DataStructures(ArrayList<String> myQuestions ) {
        myQuestion = myQuestions;
        myAnswer = new ArrayList [myQuestion.size()];
        authorOfAnswer= new ArrayList[myQuestion.size()];
        for (int i = 0; i < myQuestion.size(); i++) {
            myAnswer[i] = new ArrayList<String>();
            authorOfAnswer[i] = new ArrayList<String>();
        }
    }



    public void setResponse(int index,String answer,String author){
        myAnswer[index].add(answer);
        authorOfAnswer[index].add(author);
    }
    public ArrayList<String> getResponse(int index){
        return myAnswer[index];
    }
    public ArrayList<String> getAuthor(int index){
        return authorOfAnswer[index];
    }

    public int getLenght(int index){
        return myAnswer[index].size();
    }

    public void createAnswer(int number,ArrayList<String>  question){


        for (int i = 0; i <myAnswer.length;i++){
            myAnswer[i]= new ArrayList<String>();
            authorOfAnswer[i]= new ArrayList<String>();}
        myQuestion = question;

    }

    public ArrayList<String> getQuestion(){
        return myQuestion;
    }

}
