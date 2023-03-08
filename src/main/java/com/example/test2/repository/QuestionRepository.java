package com.example.test2.repository;

import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.model.Question;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<QuestionIndexDto> findAll(JPQLQuery<QuestionIndexDto> query, Pageable pageable);
}
