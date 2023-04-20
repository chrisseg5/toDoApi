package com.example.test2.service;

import com.example.test2.model.Candidate;
import org.springframework.stereotype.Service;

@Service
public interface CandidateService {
    Candidate saveCandidate( Candidate candidate);
}
