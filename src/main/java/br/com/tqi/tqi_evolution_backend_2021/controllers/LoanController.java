package br.com.tqi.tqi_evolution_backend_2021.controllers;

import br.com.tqi.tqi_evolution_backend_2021.dto.DetailsLoanDTO;
import br.com.tqi.tqi_evolution_backend_2021.dto.LoanDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import br.com.tqi.tqi_evolution_backend_2021.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping (value = "/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping (value="/create")
    public ResponseEntity<LoanDTO> save (@RequestBody Loan loan){
        return ResponseEntity.ok().body(loanService.save(loan));
    }

    @GetMapping (value = "/details/{id}")
    public DetailsLoanDTO findDetails (@PathVariable Long id){
        return loanService.findDetails(id);
    }
}
