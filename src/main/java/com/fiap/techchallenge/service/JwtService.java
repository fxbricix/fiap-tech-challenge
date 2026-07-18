package com.fiap.techchallenge.service;

import com.fiap.techchallenge.exception.TokenGenerateException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
    }

    public String gerarToken(UserDetails usuario) {
        try {
            String role = null;
            var auth = usuario.getAuthorities();
            if (auth != null && auth.iterator().hasNext()) {
                role = auth.iterator().next().getAuthority();
            }
            return Jwts.builder()
                    .setSubject(usuario.getUsername())
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(getKey())
                    .compact();
        } catch (Exception ex) {
            throw new TokenGenerateException("Erro ao gerar token JWT");
        }
    }

    public String extrairLogin(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenExpirado(String token){
        Date expiracao = extrairClaim(token, Claims::getExpiration);
        return expiracao.before(new Date());
    }

    public boolean tokenValido(
            String token,
            UserDetails usuario) {

        String login = extrairLogin(token);

        return login.equals(usuario.getUsername())
                && !isTokenExpirado(token);
    }

    public <T> T extrairClaim(
            String token,
            Function<Claims, T> resolver) {

        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }
}
