package com.example.test2.repository;

import com.example.test2.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository  extends JpaRepository<Candidate , Long> {

}
