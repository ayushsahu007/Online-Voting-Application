package in.scalive.votezy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class ElectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotBlank(message = "ElectionName is required")
    private String electionName;
    private int totalVotes;

    @OneToOne
    @JoinColumn(name = "winner_id ")
    private Candidate winner;

}
