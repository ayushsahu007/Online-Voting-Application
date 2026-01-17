package in.scalive.votezy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonIgnore
    private Candidate winner;

    @JsonProperty("winnerId")
    public Long getWinnerId(){
        return winner != null ? winner.getId():null;
    }


}
