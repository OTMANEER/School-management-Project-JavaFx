package com.otmane.app.questionV;

public class QuestionVo {
    private String question;
    private String[] reponse;
    private String solution;
    private String LeCreateur;

    public String getLeCreateur() {
        return LeCreateur;
    }

    public void setLeCreateur(String leCreateur) {
        LeCreateur = leCreateur;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getReponse() {
        return reponse;
    }

    public void setReponse(String[] reponse) {
        this.reponse = reponse;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
