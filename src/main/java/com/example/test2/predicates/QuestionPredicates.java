package com.example.test2.predicates;

import com.example.test2.args.QuestionArgs;
import com.example.test2.model.QQuestion;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;


public class QuestionPredicates {
    /**
     * Ονομασία (startsWith)
     */
    public static BooleanExpression nameStartsWith(final String name) {
        return QQuestion.question.questionText.upper().like(name + "%");
    }

    /**
     * Κριτήρια ευρετηρίου
     */
    public static BooleanBuilder createQuestionIndexPredicate(QuestionArgs args) {
        BooleanBuilder predicate = new BooleanBuilder();


        return predicate;
    }
}
