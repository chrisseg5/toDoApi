package com.example.test2.dto.gradeDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class QuestionnaireGradeDto {
    @NotEmpty
    private String name;

    private long grading;

    @Valid
    private List<QuestionGradeDto> questionGrades;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGrading() {
        return grading;
    }

    public void setGrading(long grading) {
        this.grading = grading;
    }

    public List<QuestionGradeDto> getQuestionGrades() {
        return questionGrades;
    }

    public void setQuestionGrades(List<QuestionGradeDto> questionGrades) {
        this.questionGrades = questionGrades;
    }
}
