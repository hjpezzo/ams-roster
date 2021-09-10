package br.com.mesttra.roster.repository;

import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.enums.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Page<Player> findAllByPosition(Optional<Position> position, Pageable pageable);

}
