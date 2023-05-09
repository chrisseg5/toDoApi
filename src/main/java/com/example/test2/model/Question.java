package com.example.test2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.aspectj.bridge.IMessage;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "question")
@Validated
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @NotEmpty
    @Column(name = "question_text")
    String questionText;
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "question")

    private List<Answer> answerList=new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "questionList")
    @JsonIgnore
    private List<Questionnaire> questionnaires = new ArrayList<>() ;


    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private List<Grading> gradings;

    public List<Grading> getGradings() {
        return gradings;
    }

    public void setGradings(List<Grading> gradings) {
        this.gradings = gradings;
    }

    public Question() {
    }

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public Question(String questionText, boolean b) {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

}
