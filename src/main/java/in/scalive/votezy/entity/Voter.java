package in.scalive.votezy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Data
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Name is Required")
    @Email(message = "Invalid mail is format")
    private String email;

    private boolean hasVoted = false;

    @OneToOne(mappedBy = "voter",cascade = CascadeType.ALL)
    private Vote vote;

}
