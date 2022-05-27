package com.kindnessocean.api.security.jwt;

import com.kindnessocean.api.security.domain.EmailAuthenticationToken;
import com.kindnessocean.api.security.domain.EmailUserDetails;
import com.kindnessocean.api.security.interf.EmailUserDetailsService;
import com.kindnessocean.shared.sql.entity.RoleType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final EmailUserDetailsService emailUserDetailsService;
    @Value("${com.kindnessocean.api.auth.token.validityTime}")
    private Long expireTime;
    @Value("${com.kindnessocean.api.auth.secret}")
    private String secret;
    private String secretKey;

    public JwtTokenProvider(final EmailUserDetailsService emailUserDetailsService) {
        this.emailUserDetailsService = emailUserDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String address, Set<RoleType> roleTypes) {

        Claims claims = Jwts.claims().setSubject(address);
        claims.put(
                "roles",
                roleTypes.stream()
                        .map(RoleType::getValue)
                        .collect(Collectors.toList())
        );

        Date now = new Date();
        Date validity = new Date(now.getTime() + expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String email = getEmail(token);

        EmailUserDetails userDetails = this.emailUserDetailsService.loadUserByEmail(email);
        return new EmailAuthenticationToken(email, userDetails.getAuthorities());
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

}
