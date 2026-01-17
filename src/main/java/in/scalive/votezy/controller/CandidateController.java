package in.scalive.votezy.controller;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate){
        Candidate savedCandiate = candidateService.addCandidate(candidate);
        return new ResponseEntity<>(savedCandiate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandiate(){
        List<Candidate> candidateList = candidateService.getAll();
        return new ResponseEntity<>(candidateList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById(@PathVariable Long id){
        Candidate candidate = candidateService.getById(id);
        return new ResponseEntity<>(candidate,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCadidate(@PathVariable Long id ,@RequestBody Candidate candidate){
        Candidate update = candidateService.updateCandidate(id, candidate);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCadidate(@PathVariable Long id){
        candidateService.deleteCadidate(id);
        return new ResponseEntity<>("Candidate with id "+id+ " deleted ",HttpStatus.OK );
    }


}
