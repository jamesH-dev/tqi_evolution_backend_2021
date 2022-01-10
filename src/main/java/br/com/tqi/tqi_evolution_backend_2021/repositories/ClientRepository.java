package br.com.tqi.tqi_evolution_backend_2021.repositories;

import br.com.tqi.tqi_evolution_backend_2021.dto.ClientDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {

    @Query ("SELECT obj FROM Client obj JOIN FETCH obj.loans WHERE obj = :client")
    ClientDTO findClientLoans(Client client);

    Client findByEmail (String email);

}
