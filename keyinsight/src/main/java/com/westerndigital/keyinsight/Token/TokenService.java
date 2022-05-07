package com.westerndigital.keyinsight.Token;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.westerndigital.keyinsight.JiraUser.JiraUser;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class TokenService {

    private Dotenv dotEnv = Dotenv.load();
    private final String SECRET_KEY = dotEnv.get("SECRET_KEY");
    private Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
    private JWTVerifier verifier = JWT.require(algorithm).build();
    private DecodedJWT decodedJWT;

    private final int HOUR_MILLIS = 60 * 60 * 1000;

    
    public Map<String, String> generateNewTokens(JiraUser jiraUser) {
        String username = jiraUser.getUsername();
        String serverUrl = jiraUser.getServerUrl();
        List<String> roles = new ArrayList<>();

        jiraUser.getAuthorities().stream()
            .forEach(authority -> {
                roles.add(authority.getAuthority());
            });

        String access_token = JWT.create()
            .withSubject(username)
            .withIssuer(serverUrl)
            .withExpiresAt(new Date(System.currentTimeMillis() + HOUR_MILLIS))
            .withClaim("role", roles)
            .sign(algorithm);

        String refresh_token = JWT.create()
            .withSubject(username)
            .withIssuer(serverUrl)
            .withExpiresAt(new Date(System.currentTimeMillis() + 
                HOUR_MILLIS * 24))
            .sign(algorithm);
        
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        return tokens;
    }

    public String getUsernameFromToken(String token) {
        decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public Map<String, String> createErrorMap(String errorMessage) {
        Map<String, String> error = new HashMap<>();
        error.put("error_message", errorMessage);

        return error;
    }
}
