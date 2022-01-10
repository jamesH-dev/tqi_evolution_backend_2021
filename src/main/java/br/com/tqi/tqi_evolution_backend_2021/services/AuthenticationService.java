package br.com.tqi.tqi_evolution_backend_2021.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.Collections.emptyList;
import java.util.Date;

public class AuthenticationService {
    static final long EXPIRATION_TIME = 864_000_00;
    static final String SIGNING_KEY = "RedPill";
    static final String PREFIX = "Bearer";

    static public void addToken(HttpServletResponse response, String email){
        String JwToken = Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
        response.addHeader("Authorization", PREFIX + " " + JwToken);
        response.addHeader("Access-Control-Expose-Headers", "Authorization ");
    }
    static public Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null){
            String email = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (email != null)
                return new UsernamePasswordAuthenticationToken(email, null, emptyList());
        }
        return null;
    }
}
