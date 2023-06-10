package com.example.test2.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "toDoTasks")
@Validated
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String text;
    private boolean done;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;


    public ToDo() {
    }


    public ToDo(long id, String text, boolean done, Date date) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
