package com.westerndigital.keyinsight.Token;

import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class TokenService {

    private Dotenv dotEnv = Dotenv.load();
    private final String SECRET_KEY = dotEnv.get("SECRET_KEY");
    private Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
    private JWTVerifier verifier = JWT.require(algorithm).build();
    private DecodedJWT decodedJWT;
    
    public Map<String, String> generateNewToken() {

        return null;
    }

    public String getUsernameFromToken(String token) {
        decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
