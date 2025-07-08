package utez.edu.mx.unidad3.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtils {
    @Value("${secret.key}")
    private String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
       return extractClaims(token,Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims,T > claimsResolver){
        final Claims CLAIMS = extractAllClaims(token);
        return claimsResolver.apply(CLAIMS);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Consume la funcion de arriba adicional a que pregunta que el usuario del token
    // coincida con el usuario que lo usa
    //UserDetails = pasaporte
    public Boolean validateToken(String token, UserDetails userDetails){
        final String USERNAME = extractUsername(token);
        return (USERNAME.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Crea el token a raiz de la informacion del usuario y el token
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims).setSubject(subject) // informacion del usuario
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(
                        System.currentTimeMillis() + 1000 * 60 * 60 * 10
                ))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
         //Construir un token
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    //Consume la funcion de crear apra solamente exportar el token



}
