package br.com.tqi.tqi_evolution_backend_2021.controllers;

import br.com.tqi.tqi_evolution_backend_2021.dto.DetailsLoanDTO;
import br.com.tqi.tqi_evolution_backend_2021.dto.LoanDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import br.com.tqi.tqi_evolution_backend_2021.services.ClientService;
import br.com.tqi.tqi_evolution_backend_2021.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RestController
@RequestMapping (value = "/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private ClientService clientService;

    Client findLoggedInClient(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return clientService.findClientByEmail(email);

    }
    @PostMapping (value="/create")
    public ResponseEntity save (@RequestBody Loan loan){
        loan.setDateLoan(LocalDateTime.now());
        loan.setClient(findLoggedInClient());
        LocalDate firstInstallment = loan.getFirstInstallment();
        if (loan.getInstallments() <= 60 && firstInstallment.isBefore(loan.getDateLoan().plusMonths(3).toLocalDate())){
            Loan saved = loanService.save(loan);
            return ResponseEntity.ok().body(new LoanDTO(saved));
        } else {
            return ResponseEntity.badRequest().body("As parcelas devem ser inferiores a 60 e a data da primeira parcela deverá ser inferior a 3 meses");
        }
    }

    @GetMapping (value = "/details/{id}")
    public DetailsLoanDTO findDetails (@PathVariable Long id){
        return loanService.findDetails(id);
    }
}
