package br.com.mesttra.roster.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.mesttra.roster.dto.MonthlyExpenseDTO;

@FeignClient(name = "financial", url="http://localhost:8082/")
public interface FinancialClient {

    @RequestMapping(value = "/expenses", method = RequestMethod.POST)
    MonthlyExpenseDTO registerMonthlyExpense(@RequestBody MonthlyExpenseDTO monthlyExpenseDTO);

}
