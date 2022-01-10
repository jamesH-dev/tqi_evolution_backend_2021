package br.com.tqi.tqi_evolution_backend_2021.services;

import br.com.tqi.tqi_evolution_backend_2021.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        LoginDTO currentClient = clientService.findCredentials(email);
        return new org.springframework.security.core
                .userdetails.User(email, currentClient.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
    }
}
