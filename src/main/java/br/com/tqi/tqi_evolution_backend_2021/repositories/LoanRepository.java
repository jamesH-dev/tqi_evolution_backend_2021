package br.com.tqi.tqi_evolution_backend_2021.repositories;

import br.com.tqi.tqi_evolution_backend_2021.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
