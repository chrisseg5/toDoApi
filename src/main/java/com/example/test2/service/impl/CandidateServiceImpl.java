package com.example.test2.service.impl;

import com.example.test2.model.Candidate;
import com.example.test2.repository.CandidateRepository;
import com.example.test2.service.CandidateService;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {
    private  CandidateRepository candidateRepository ;

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
