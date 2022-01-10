package br.com.tqi.tqi_evolution_backend_2021.controllers;

import br.com.tqi.tqi_evolution_backend_2021.dto.ClientDTO;
import br.com.tqi.tqi_evolution_backend_2021.dto.LoginDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping (value = "/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping (value = "/{id}")
    public ResponseEntity clients(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping (value = "/create")
    public ResponseEntity insertClient(@RequestBody Client client){
        client.setPassword(encoder.encode(client.getPassword()));
        Client saved = clientService.save(client);
        return ResponseEntity.ok().body(new ClientDTO(saved));
    }
}
