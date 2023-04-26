package com.example.test2.dto.gradeDto;

import javax.validation.constraints.NotEmpty;

public class QuestionGradeDto {
    @NotEmpty
    private String questionText;

    private Integer grade;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
