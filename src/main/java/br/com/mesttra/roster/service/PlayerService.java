package br.com.mesttra.roster.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.mesttra.roster.dto.MonthlyExpenseDTO;
import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.repository.PlayerRepository;
import br.com.mesttra.roster.rest.FinancialClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PlayerService {

	PlayerRepository playerRepository;
	FinancialClient financialClient;

	public Player addPlayer(Player player) {
		// send to financial
		MonthlyExpenseDTO registerMonthlyExpense = financialClient
				.registerMonthlyExpense(new MonthlyExpenseDTO(0L, "SALARY", player.getSalary(), LocalDate.now()));

		return playerRepository.save(player);
	}

	public List<Player> listPlayers() {
		return playerRepository.findAll();
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
		playerRepository.save(player);

		return player;
	}

	@Transactional
	public void makePlayerUnavailable(Long playerId) {

		Player player = playerRepository.getById(playerId);
		player.setAvailable(false);
		playerRepository.save(player);

	}
}
