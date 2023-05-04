package com.example.test2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String name ;
    private String surName;
    private String email;
    private long finalScore;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionnaire_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Questionnaire questionnaire;

    public Candidate() {
    }

    public Candidate(long id, String name, String surName, String email, long finalScore, Questionnaire questionnaire) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.finalScore = finalScore;
        this.questionnaire = questionnaire;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(long finalScore) {
        this.finalScore = finalScore;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}
