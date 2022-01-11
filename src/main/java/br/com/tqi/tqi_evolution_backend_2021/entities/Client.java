package br.com.tqi.tqi_evolution_backend_2021.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "tab_client")
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "client_id")
    private Long id;
    @Column (name = "client_email")
    private String email;
    @Column (name = "client_full_name")
    private String fullName;
    @Column (name = "client_income")
    private BigDecimal income;
    @Column (name = "client_cpf")
    private String cpf;
    @Column (name="client_rg")
    private String rg;
    @Column (name = "client_full_address")
    private String fullAddress;
    @Column (name = "client_password")
    private String password;

    @OneToMany (mappedBy = "client", fetch = FetchType.LAZY)
    Set<Loan> loans = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(email, client.email) && Objects.equals(fullName, client.fullName) && Objects.equals(income, client.income) && Objects.equals(cpf, client.cpf) && Objects.equals(rg, client.rg) && Objects.equals(fullAddress, client.fullAddress) && Objects.equals(password, client.password) && Objects.equals(loans, client.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, fullName, income, cpf, rg, fullAddress, password);
    }
}
