package br.com.tqi.tqi_evolution_backend_2021.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "tab_loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;
    @Column(name="loan_amount")
    private BigDecimal amount;
    @Column(name = "loan_installments")
    private Integer installments;
    @Column(name = "loan_date")
    private LocalDateTime dateLoan;
    @Column(name = "loan_first_installment")
    private LocalDate firstInstallment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id) && Objects.equals(amount, loan.amount) && Objects.equals(installments, loan.installments) && Objects.equals(dateLoan, loan.dateLoan) && Objects.equals(firstInstallment, loan.firstInstallment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, installments, dateLoan, firstInstallment);
    }
}
