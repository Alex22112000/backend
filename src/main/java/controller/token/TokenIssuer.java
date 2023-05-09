package controller.token;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenIssuer {
    
    private Key key;

    public TokenIssuer(Key key){
        this.key = key;
    }

    public String issueToken(String login, String role){
        LocalDateTime expiryPeriod = LocalDateTime.now().plusMinutes(600L); // длительность токена
        Date expirationDateTime = Date.from(
            expiryPeriod.atZone(ZoneId.systemDefault())
            .toInstant());
        Claims claims = Jwts.claims();
        claims.setSubject(login);
        claims.put("role", role);

        String compactJws = Jwts.builder()
            .setClaims(claims)
            .signWith(key, SignatureAlgorithm.HS256)
            .setIssuedAt(new Date())
            .setExpiration(expirationDateTime)
            .compact();
        return compactJws;
    }
}