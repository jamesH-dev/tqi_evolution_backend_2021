package br.com.tqi.tqi_evolution_backend_2021.config;

import br.com.tqi.tqi_evolution_backend_2021.services.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException{
        Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);


    }
}
