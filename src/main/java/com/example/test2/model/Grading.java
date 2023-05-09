package com.example.test2.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Entity
@Table(name = "grading")
@Validated
public class Grading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire ;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question ;

    @Column(name = "grade")
    private int grade;

    public Grading() {
    }

    public Grading(long id, Questionnaire questionnaire, Question question, int grade) {
        this.id = id;
        this.questionnaire = questionnaire;
        this.question = question;
        this.grade = grade;
    }

    public Grading(Questionnaire questionnaire, Question question) {
        this.questionnaire =questionnaire;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
