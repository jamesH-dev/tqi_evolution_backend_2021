package br.com.tqi.tqi_evolution_backend_2021.services;

import br.com.tqi.tqi_evolution_backend_2021.dto.DetailsLoanDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import br.com.tqi.tqi_evolution_backend_2021.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan save (Loan loan){
        return loanRepository.save(loan);
    }

    public DetailsLoanDTO findDetails (Long id){
        Optional <Loan> found = loanRepository.findById(id);
        if (found.isPresent()){
            return new DetailsLoanDTO(found.get());
        } else {
            return new DetailsLoanDTO(new Loan());
        }
    }


}
