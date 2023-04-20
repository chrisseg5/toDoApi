package com.example.test2.dto.index;

import java.util.Date;

public class QuenstionnaireIndexDto {
    private Long id;
    private String name;
    private Date date;
    private Long grading;

    public Long getGrading() {
        return grading;
    }

    public void setGrading(Long grading) {
        this.grading = grading;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
