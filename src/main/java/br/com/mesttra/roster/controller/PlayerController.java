package br.com.mesttra.roster.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mesttra.roster.dto.AvaliabilityDTO;
import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	PlayerService playerService;

	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@PostMapping
	public Player addPlayer(@RequestBody Player player) {
		return playerService.addPlayer(player);
	}

	@GetMapping
	public List<Player> listPlayers() {
		return playerService.listPlayers();
	}

	@GetMapping(path = "/{id}")
	public Optional<Player> getPlayer(@PathVariable Long id) {
		return playerService.getPlayer(id);
	}

	@DeleteMapping(path = "/{id}")
	public String removePlayer(@PathVariable Long id) {
		return playerService.removePlayer(id);
	}

	@PatchMapping(path = "/{id}")
	public Player changePlayerAvailability(@PathVariable Long id, @RequestBody AvaliabilityDTO availabilityDTO) {
		return playerService.changePlayerAvailability(id, availabilityDTO.isAvailable());
	}

}
