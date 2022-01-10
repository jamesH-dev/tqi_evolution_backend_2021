package br.com.tqi.tqi_evolution_backend_2021.config;

import br.com.tqi.tqi_evolution_backend_2021.dto.LoginDTO;
import br.com.tqi.tqi_evolution_backend_2021.services.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    public LoginFilter (String url, AuthenticationManager authManager){
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        LoginDTO dto = new ObjectMapper()
                .readValue(request.getInputStream(), LoginDTO.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword(), Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth)
            throws IOException, ServletException {
        AuthenticationService.addToken(response, auth.getName());
    }
}
