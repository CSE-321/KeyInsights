package com.westerndigital.keyinsight.Token;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    public String generateNewAccessToken(JiraUser user, String requestUrl) {
        String username = user.getUsername();
        List<String> roles = new ArrayList<>();

        final int ACCESS_TOKEN_EXPIRATION_MILLIS = 30 * 60 * 1000; 

        user.getAuthorities().stream()
            .forEach(authority -> {
                roles.add(authority.getAuthority());
            });

        String access_token = JWT.create()
            .withSubject(username)
            .withIssuer(requestUrl)
            .withClaim("role", roles)
            .withExpiresAt(new Date(System.currentTimeMillis() + 
                ACCESS_TOKEN_EXPIRATION_MILLIS))
            .sign(algorithm);

        return access_token;

    }

    public String getUsernameFromToken(String token) {
        decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

}
