package in.scalive.votezy.service;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    public Candidate getById(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if (candidate == null) {
            throw new ResourceNotFoundException("Candidate Id " + id + " Not Found");
        }
        return candidate;
    }

    public Candidate updateCandidate(Long id, Candidate updatedcandidate) {
        Candidate candidate = candidateRepository.getById(id);
        if (updatedcandidate.getName() != null) {
            candidate.setName(updatedcandidate.getName());
        }
        if (updatedcandidate.getParty() != null) {
            candidate.setParty(updatedcandidate.getParty());
        }
        return candidateRepository.save(candidate);
    }

    public void deleteCadidate(Long id){
        Candidate candidate = candidateRepository.getById(id);
        List<Vote> votes = candidate.getVote();
        for (Vote v : votes){
            v.setCandidate(null);
        }
        candidate.getVote().clear();
        candidateRepository.delete(candidate);
    }

}
