package in.scalive.votezy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is Required")
    private  String name;

    @NotBlank(message = "party is Required")
    private String party;

    private int voteCount = 0;

    @OneToMany(mappedBy = "candidate" ,cascade = CascadeType.ALL )
    private List<Vote> vote;
}
