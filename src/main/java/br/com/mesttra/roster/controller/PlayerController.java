package br.com.mesttra.roster.controller;

import br.com.mesttra.roster.dto.AvailabilityDTO;
import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.enums.Position;
import br.com.mesttra.roster.service.PlayerService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Player changePlayerAvailability(@PathVariable Long id, @RequestBody AvailabilityDTO availabilityDTO) {
        return playerService.changePlayerAvailability(id, availabilityDTO.isAvailable());
    }


}
