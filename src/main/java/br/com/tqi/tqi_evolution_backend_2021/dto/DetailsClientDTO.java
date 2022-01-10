package br.com.tqi.tqi_evolution_backend_2021.dto;

import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DetailsClientDTO {
    private String email;
    private BigDecimal income;


    public DetailsClientDTO(Client client){
        email = client.getEmail();
        income = client.getIncome();

    }
}
