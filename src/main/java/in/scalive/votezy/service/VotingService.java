package in.scalive.votezy.service;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.entity.Voter;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.exception.VoteNotAllowedException;
import in.scalive.votezy.repository.CandidateRepository;
import in.scalive.votezy.repository.VoteRepository;
import in.scalive.votezy.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {

    private VoteRepository voteRepository;
    private CandidateRepository candidateRepository;
    private VoterRepository voterRepository;

    @Autowired
    public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    public Vote castVote(Long voterID,Long candidateId){
        if (!voterRepository.existsById(voterID)){
            throw new ResourceNotFoundException("Voter not found with ID : "+ voterID);
        }
        if (!candidateRepository.existsById(candidateId)){
            throw new ResourceNotFoundException("Candiate not found with ID :"+ candidateId);
        }

        Voter voter = voterRepository.findById(voterID).get();
        if (voter.isHasVoted()){
            throw new VoteNotAllowedException("Voter ID :"+voterID+"has already casted");
        }

        Candidate candidate = candidateRepository.findById(candidateId).get();
        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);
      //  voteRepository.save(vote);

        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepository.save(candidate);

        voter.setVote(vote);
        voter.setHasVoted(true);
        voterRepository.save(voter);
        return vote;
    }
public List<Vote> getAllVotes(){
        return voteRepository.findAll();
}
}
