package br.com.tqi.tqi_evolution_backend_2021.services;

import br.com.tqi.tqi_evolution_backend_2021.dto.ClientDTO;
import br.com.tqi.tqi_evolution_backend_2021.dto.LoginDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity getClientById(Long id){
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isPresent()){
            clientRepository.findClientLoans(optional.get());
            return ResponseEntity.ok().body(new ClientDTO(optional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public LoginDTO findCredentials (String email){
        Client client = clientRepository.findByEmail(email);
        return new LoginDTO(client);

    }

    public Client findClientByEmail (String email){
        return clientRepository.findByEmail(email);
    }

    public Client save(Client client){
        return clientRepository.save(client);
    }
}
