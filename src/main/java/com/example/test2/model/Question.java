package com.example.test2.model;

import com.sun.istack.NotNull;
import org.aspectj.bridge.IMessage;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "question")

    private List<Answer> answerList=new ArrayList<>();

    public Question() {
    }

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public Question(String questionText, boolean b) {
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
}
