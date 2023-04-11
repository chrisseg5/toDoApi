package com.example.test2.model;

import javax.persistence.*;

@Entity
public class Grading {
    @EmbeddedId
    private GradingId gradingId = new GradingId() ;
    @ManyToOne
    @MapsId("questionnaireId")
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;
    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

    private int gradingForQuestion ;

    public GradingId getGradingId() {
        return gradingId;
    }

    public void setGradingId(GradingId gradingId) {
        this.gradingId = gradingId;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getGradingForQuestion() {
        return gradingForQuestion;
    }

    public void setGradingForQuestion(int gradingForQuestion) {
        this.gradingForQuestion = gradingForQuestion;
    }
}
