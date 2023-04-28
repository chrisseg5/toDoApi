package com.example.test2.model;

import javax.persistence.*;

@Entity
@Table(name = "gradingg")
public class Gradingg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @Column(name = "grading")
    private int grading;

    public Gradingg() {
    }

    public Gradingg(Long id, Question question, Questionnaire questionnaire, int grading) {
        this.id = id;
        this.question = question;
        this.questionnaire = questionnaire;
        this.grading = grading;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public int getGrading() {
        return grading;
    }

    public void setGrading(int grading) {
        this.grading = grading;
    }
}
