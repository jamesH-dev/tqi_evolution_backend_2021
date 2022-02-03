package br.com.tqi.tqi_evolution_backend_2021.dto;

import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DetailsLoanDTO {
    private Long id;
    private BigDecimal amount;
    private Integer installments;
    private LocalDate firstInstallment;
    private DetailsClientDTO client;


    public DetailsLoanDTO(Loan loan){
        id = loan.getId();
        amount = loan.getAmount();
        installments = loan.getInstallments();
        firstInstallment = loan.getFirstInstallment();
        client = new DetailsClientDTO(loan.getClient());
    }
}
