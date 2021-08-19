package br.com.mesttra.roster.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MonthlyExpenseDTO {

	private Long id;
	private String expenseType;
	private Double amount;
	private LocalDate dueDate;

	public MonthlyExpenseDTO(Long id, String expenseType, Double amount, LocalDate dueDate) {
		super();
		this.id = id;
		this.expenseType = expenseType;
		this.amount = amount;
		this.dueDate = dueDate;
	}

}
