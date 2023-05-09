package com.example.test2.repository;

import com.example.test2.model.Grading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradingRepository extends JpaRepository<Grading , Long> {

    Optional<Grading> findByQuestionnaireIdAndQuestionId(Long questionnaireId, Long questionId);
}
