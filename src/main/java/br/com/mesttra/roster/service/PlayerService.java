package br.com.mesttra.roster.service;

import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.enums.Position;
import br.com.mesttra.roster.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlayerService {

    PlayerRepository playerRepository;

    //@CacheEvict(cacheNames = "players", allEntries = true)
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    //@Cacheable(cacheNames = "players", key="#root.method.name")
    public Page<Player> listPlayers(Optional<Position> position, Pageable pageable) {
        if(position.isEmpty()) {
            return playerRepository.findAll(pageable);
        }
        return playerRepository.findAllByPosition(position, pageable);
    }

    public Optional<Player> getPlayer(Long playerId) {
        return playerRepository.findById(playerId);
    }

    public String removePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
        return "Removido com sucesso";
    }

    @Transactional
    public Player changePlayerAvailability(Long playerId, boolean available) {

        Player player = playerRepository.getById(playerId);
        player.setAvailable(available);
        return playerRepository.save(player);

    }

    @Transactional
    public void makePlayerUnavailable(Long playerId) {

        Player player = playerRepository.getById(playerId);
        player.setAvailable(false);
        playerRepository.save(player);

    }
}
