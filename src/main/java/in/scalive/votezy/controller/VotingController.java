package in.scalive.votezy.controller;

import in.scalive.votezy.dto.VoteRequestDTO;
import in.scalive.votezy.dto.VoteResponseDTO;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
public class VotingController {

    private VotingService votingService;

    @Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }
      @PostMapping("/cast")
    public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequestDTO){
          Vote vote = votingService.castVote(voteRequestDTO.getVoterId(), voteRequestDTO.getCandidateId());
         VoteResponseDTO voteResponse =  new VoteResponseDTO("Vote casted successfully",true,vote.getVoterId(),vote.getCandidateId());
         return  new ResponseEntity<>(voteResponse, HttpStatus.CREATED);
      }
}
