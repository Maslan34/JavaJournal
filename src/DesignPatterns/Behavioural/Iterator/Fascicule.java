package DesignPatterns.Behavioural.Iterator;

public class Fascicule {
    private String fasiculeName;
    private Question[] questions;

    public Fascicule(String fasiculeName) {
        this.fasiculeName = fasiculeName;
        questions = new Question[10];
        questions[0]= new Question(6);
        questions[1]= new Question(7);
        questions[2]= new Question(8);
        questions[3]= new Question(9);
        questions[4]= new Question(10);
        questions[5]= new Question(11);
        questions[6]= new Question(12);
        questions[7]= new Question(13);
        questions[8]= new Question(14);
        questions[9]= new Question(15);
    }

    public String getFasiculeName() {
        return fasiculeName;
    }

    public void setFasiculeName(String fasiculeName) {
        this.fasiculeName = fasiculeName;
    }

    /* Before Iterator
    public Question[] getQuestions() {
        return questions;
    }

     */

    public QuestionIterator getQuestions() {
        return new QuestionIterator(questions);
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
