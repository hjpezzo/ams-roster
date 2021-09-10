package br.com.mesttra.roster.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mesttra.roster.dto.AvaliabilityDTO;
import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.enums.Position;
import br.com.mesttra.roster.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/players")
@Api(value = "Players", tags = {"Players"})
public class PlayerController {

	PlayerService playerService;

	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add player",
            notes = "This method adds a new player")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Player added"),
            @ApiResponse(code = 500, message = "Internal Error"),
    })
	public Player addPlayer(@RequestBody Player player) {
		return playerService.addPlayer(player);
	}

    @GetMapping
    public Page<Player> listPlayer(@PageableDefault(page = 0, size = 20) Pageable pageable, @RequestParam(required = false) Optional<Position> position) {
        return playerService.listPlayers(position, pageable);
    }

	@GetMapping
	public List<Player> listPlayers() {
		return playerService.listPlayers();
	}

	@GetMapping(path = "/{id}")
    public Optional<Player> getPlayer(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "Player id",
                    example = "2",
                    required = true)
            @PathVariable Long id) {
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
