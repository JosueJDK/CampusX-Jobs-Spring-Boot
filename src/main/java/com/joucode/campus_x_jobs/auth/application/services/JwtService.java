package com.joucode.campus_x_jobs.auth.application.services;

import com.joucode.campus_x_jobs.common.exceptions.CustomAuthenticationException;
import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.domain.models.Role;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.joucode.campus_x_jobs.common.constants.ConstantsJwtService.ROLE_CLAIM;
import static com.joucode.campus_x_jobs.common.constants.ConstantsJwtService.TOKEN_TYPE_CLAIM;

@Component
@Slf4j
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractTypeToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("token_type").toString();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String generateToken(String id, String login, RoleName roles, String tokenType) {
        return Jwts.builder()
                .setSubject(login)
                .claim("id", id)
                .claim(TOKEN_TYPE_CLAIM, tokenType)
                .claim(ROLE_CLAIM, roles)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Map<String, String> generateTokens(String id, String login, RoleName roles) {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", generateToken(id, login, roles, "access_token"));
        tokens.put("refreshToken", generateToken(id, login, roles, "refresh_token"));
        return tokens;
    }

    public boolean validateToken(String token) throws CustomAuthenticationException {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException |
                 ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException | MalformedJwtException e) {
            log.info("Invalid JWT: {}", e.getMessage());
            log.trace("Invalid JWT trace: {}", e);
            throw new CustomAuthenticationException(e.getMessage());
        }
    }

    public String getToken(HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
