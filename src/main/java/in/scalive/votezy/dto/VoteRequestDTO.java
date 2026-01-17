package in.scalive.votezy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class VoteRequestDTO {
    @NotNull(message = "Voter ID is required")
    Long voterId;

    @NotNull(message = "Candidate ID is required")
    Long candidateId;

}
