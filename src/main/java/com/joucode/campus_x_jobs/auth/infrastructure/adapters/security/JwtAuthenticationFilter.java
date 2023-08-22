package com.joucode.campus_x_jobs.auth.infrastructure.adapters.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joucode.campus_x_jobs.auth.application.services.JwtService;
import com.joucode.campus_x_jobs.auth.application.services.UserDetailsServiceImpl;
import com.joucode.campus_x_jobs.common.exceptions.CustomAuthenticationException;
import com.joucode.campus_x_jobs.common.response.Response;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtService.getToken(request);

        if (token != null) {
            try {

                if(jwtService.validateToken(token)){
                    String login = jwtService.extractEmail(token);

                    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(login);
                    if (userDetails != null) {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

                        LOGGER.info("Authenticate user with email: {}", login);

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (CustomAuthenticationException e) {
                LOGGER.info("Authentication error: {}", e.getMessage());
                handleAuthenticationError(response, e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleAuthenticationError(HttpServletResponse servletResponse, String errorMessage) throws IOException {

        servletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final ObjectMapper mapper = new ObjectMapper();

        Response responseBody = new Response();
        responseBody.setCode(UNAUTHORIZED.value());
        responseBody.setStatus(UNAUTHORIZED.name());
        responseBody.setData(Map.of("error", Map.of("message", errorMessage)));

        mapper.writeValue(servletResponse.getOutputStream(), responseBody);
    }
}
