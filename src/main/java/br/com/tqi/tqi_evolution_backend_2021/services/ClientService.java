package br.com.tqi.tqi_evolution_backend_2021.services;

import br.com.tqi.tqi_evolution_backend_2021.dto.ClientDTO;
import br.com.tqi.tqi_evolution_backend_2021.dto.LoginDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.exceptions.AlreadyExistsException;
import br.com.tqi.tqi_evolution_backend_2021.exceptions.ClientNotFoundException;
import br.com.tqi.tqi_evolution_backend_2021.repositories.ClientRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;


@Service
public class ClientService {


    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO getClientById(Long id){
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isPresent()){
            clientRepository.findClientLoans(optional.get());
            return new ClientDTO(optional.get());
        }
        throw new ClientNotFoundException("Cliente não foi encontrado!");
    }

    public LoginDTO findCredentials (String email){
        Client client = clientRepository.findByEmail(email);
        return new LoginDTO(client);
    }

    public Client findClientByEmail (String email){
        return clientRepository.findByEmail(email);
    }

    public ClientDTO findAuthenticatedClient (String token){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Object obj = JSONValue.parse(payload);
        JSONObject jsonObjectDecode = (JSONObject)obj;
        String email = (String)jsonObjectDecode.get("sub");
        return new ClientDTO(clientRepository.findByEmail(email));
    }

    public Client save(Client client){
        Client found = clientRepository.findByEmail(client.getEmail());
        if (found != null) throw new AlreadyExistsException("Email já cadastrado!");
        return clientRepository.save(client);
    }
}
