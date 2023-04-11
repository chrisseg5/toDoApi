package com.example.test2.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GradingId implements Serializable {
    private static final long serialVersionUID = -8180257034989631458L;
    private Long questionnaireId;
    private Long questionId;

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradingId gradingId)) return false;
        return getQuestionnaireId().equals(gradingId.getQuestionnaireId()) && getQuestionId().equals(gradingId.getQuestionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionnaireId(), getQuestionId());
    }
}
