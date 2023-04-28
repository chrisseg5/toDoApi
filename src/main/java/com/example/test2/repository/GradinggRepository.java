package com.example.test2.repository;

import com.example.test2.model.Gradingg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  GradinggRepository extends JpaRepository<Gradingg , Long> {
    List<Gradingg> findByQuestionnaireId(Long questionnaireId);

}
