package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player>findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select p from Player as p join p.team as t where t.name =:name")
    List<Player>findPlayersByTeamName(@Param(value = "name")String name);

    List<Player>findAllBySalaryAfterOrderBySalaryDesc(BigDecimal salary);
}
