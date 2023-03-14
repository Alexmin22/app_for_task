package com.example.app_for_task.security;

import com.example.app_for_task.dto.auth.JwtResponse;
import com.example.app_for_task.exception.ViolationAccesException;
import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.model.consumer.Role;
import com.example.app_for_task.services.ConsumerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties properties;
    private final UserDetailsService userDetailsService;
    private final ConsumerService consumerService;
    private  String key;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(properties.getSecret().getBytes());
    }

    public String createAccessToken(int consId, String name, Set<Role> roleSet) {
        Claims claims = Jwts.claims().setSubject(name);
        claims.put("id", consId);
        claims.put("roles", resolveRoles(roleSet));

        Date now = new Date();
        Date valid = new Date(now.getTime() + properties.getAccess());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(valid)
                .signWith(SignatureAlgorithm.ES256, key)
                .compact();
    }

    private List<String> resolveRoles(Set<Role> roleSet) {

        return roleSet.stream()
                .map(role -> role.name())
                .collect(Collectors.toList());
    }

    public String refreshToken(int consId, String name) {
        Claims claims = Jwts.claims().setSubject(name);
        claims.put("id", consId);

        Date now = new Date();
        Date valid = new Date(now.getTime() + properties.getRefresh());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(valid)
                .signWith(SignatureAlgorithm.ES256, key)
                .compact();
    }

    public JwtResponse refreshConsumerToken(String refreshToken) {
        JwtResponse response = new JwtResponse();

        if (!validateToken(refreshToken)) {
            throw new ViolationAccesException();
        }

        int consId = Integer.parseInt(getById(refreshToken));
        Consumer consumer = consumerService.getById(consId);
        response.setId(consId);
        response.setUserName(consumer.getFirstName());
        response.setAccessToken(createAccessToken(consId, consumer.getFirstName(), consumer.getRoleSet()));
        response.setRefreshToken(refreshToken(consId, consumer.getFirstName()));

        return response;
    }

    public boolean validateToken(String token) {
        Jws<Claims> claimsJwts = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return !claimsJwts.getBody().getExpiration().before(new Date());
    }

    private String getById(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
    }

    private String getConsName(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getConsName(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
