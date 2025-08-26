package in.scalive.votezy.repository;

import in.scalive.votezy.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {
    boolean existsByEmail(String email);

}
