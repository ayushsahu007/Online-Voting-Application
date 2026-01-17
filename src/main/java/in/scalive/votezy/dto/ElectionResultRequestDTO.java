package in.scalive.votezy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {
    @NotBlank(message = "Election Name Required")
    private String electionName;
}
