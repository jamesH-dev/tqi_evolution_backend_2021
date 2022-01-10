package br.com.tqi.tqi_evolution_backend_2021.dto;

import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LoanDTO {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime dateLoan;
    private Integer installments;
    private LocalDate firstInstallment;

    public LoanDTO(Loan loan){
        id = loan.getId();
        amount = loan.getAmount();
        dateLoan = loan.getDateLoan();
        installments = loan.getInstallments();
        firstInstallment = loan.getFirstInstallment();
    }
}
