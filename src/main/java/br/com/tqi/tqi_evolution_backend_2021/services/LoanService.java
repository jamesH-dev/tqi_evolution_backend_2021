package br.com.tqi.tqi_evolution_backend_2021.services;

import br.com.tqi.tqi_evolution_backend_2021.dto.DetailsLoanDTO;
import br.com.tqi.tqi_evolution_backend_2021.dto.LoanDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import br.com.tqi.tqi_evolution_backend_2021.exceptions.LoanDateException;
import br.com.tqi.tqi_evolution_backend_2021.repositories.LoanRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class LoanService {
    private static final String EXCEPTION_MESSAGE = "A quantidade de parcelas deve ser inferior à 60 meses e a data da primeira parcela deverá ser dentro de 3 meses.";
    private final LoanRepository loanRepository;

    private final ClientService clientService;

    public LoanService(LoanRepository loanRepository, ClientService clientService) {
        this.loanRepository = loanRepository;
        this.clientService = clientService;
    }

    public LoanDTO save (Loan loan){
        loan.setDateLoan(LocalDateTime.now());
        loan.setClient(findLoggedInClient());
        LocalDate firstInstallment = loan.getFirstInstallment();
        if (loan.getInstallments() <= 60 &&
                firstInstallment.isBefore(loan.getDateLoan().plusMonths(3).toLocalDate())) {
            Loan saved = loanRepository.save(loan);
            return new LoanDTO(saved);
        }
        throw new LoanDateException(EXCEPTION_MESSAGE);
    }

    public DetailsLoanDTO findDetails (Long id){
        Optional <Loan> found = loanRepository.findById(id);
        if (found.isPresent()){
            return new DetailsLoanDTO(found.get());
        } else {
            return new DetailsLoanDTO(new Loan());
        }
    }

    private Client findLoggedInClient(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return clientService.findClientByEmail(email);

    }




}
