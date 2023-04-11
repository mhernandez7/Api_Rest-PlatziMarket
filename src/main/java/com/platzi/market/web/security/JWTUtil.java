package com.platzi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "Platzi1234*";
    public String generateToken(UserDetails userDetails){

    return Jwts.builder().setSubject(userDetails.getUsername())
            .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, KEY).compact(); //fecha de expiracion 10 hs
    }
    //valida que el token no este vencido
    public boolean validateToken(String token, UserDetails userDetails){

        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);

    }

    //Metodo que extrae el usuario del token
    public String extractUsername(String token){

        return getClaims(token).getSubject();
    }

    //Metodo que valida si el JWT ya expiro
    public boolean isTokenExpired(String token){

        return getClaims(token).getExpiration().before(new Date());

    }

    //retorna el cuerpo del token separado por cada uno de los objetos que contiene el JWT
    private Claims getClaims (String token){

        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
