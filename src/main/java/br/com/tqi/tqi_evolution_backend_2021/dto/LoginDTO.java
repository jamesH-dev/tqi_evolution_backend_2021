package br.com.tqi.tqi_evolution_backend_2021.dto;

import br.com.tqi.tqi_evolution_backend_2021.entities.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String password;

    public LoginDTO(Client client){
        email = client.getEmail();
        password = client.getPassword();
    }
}
