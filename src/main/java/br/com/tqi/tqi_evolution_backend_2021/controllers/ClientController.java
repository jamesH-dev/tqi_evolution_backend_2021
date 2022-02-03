package br.com.tqi.tqi_evolution_backend_2021.controllers;

import br.com.tqi.tqi_evolution_backend_2021.dto.ClientDTO;
import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import br.com.tqi.tqi_evolution_backend_2021.services.AuthenticationService;
import br.com.tqi.tqi_evolution_backend_2021.services.ClientService;
import io.jsonwebtoken.Jwts;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Base64;

import static br.com.tqi.tqi_evolution_backend_2021.services.AuthenticationService.SIGNING_KEY;
import static java.util.Collections.emptyList;
import static org.aspectj.weaver.NameMangler.PREFIX;


@RestController
@RequestMapping (value = "/api/clients")
public class ClientController {

    private final ClientService clientService;

    private final PasswordEncoder encoder;

    public ClientController(ClientService clientService, PasswordEncoder encoder) {
        this.clientService = clientService;
        this.encoder = encoder;
    }


    @GetMapping
    public ResponseEntity<ClientDTO> authenticatedClient (@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(clientService.findAuthenticatedClient(token));
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<ClientDTO> clients(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @PostMapping (value = "/create")
    public ResponseEntity<ClientDTO> insertClient(@RequestBody Client client){
        client.setPassword(encoder.encode(client.getPassword()));
        Client saved = clientService.save(client);
        return ResponseEntity.ok().body(new ClientDTO(saved));
    }
}
