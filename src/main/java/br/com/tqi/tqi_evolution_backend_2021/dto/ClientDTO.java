package br.com.tqi.tqi_evolution_backend_2021.dto;

import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String fullName;
    private BigDecimal income;
    private String email;
    private String cpf;
    private String rg;
    private Date birthDate;
    private String fullAddress;
    private List <LoanDTO> loans;

    public ClientDTO (Client client){
        id = client.getId();
        fullName = client.getFullName();
        income = client.getIncome();
        email = client.getEmail();
        cpf = client.getCpf();
        rg = client.getRg();
        birthDate = client.getBirthDate();
        fullAddress = client.getFullAddress();
        loans = client.getLoans().stream().map(LoanDTO::new).collect(Collectors.toList());
    }


}
