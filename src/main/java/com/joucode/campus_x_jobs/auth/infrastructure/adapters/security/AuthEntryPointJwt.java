package com.joucode.campus_x_jobs.auth.infrastructure.adapters.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joucode.campus_x_jobs.common.response.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {

      LOGGER.error("---- Unauthorized error: {}", authException.getMessage());

      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

      Response responseBody = new Response();
      responseBody.setCode(UNAUTHORIZED.value());
      responseBody.setStatus(UNAUTHORIZED.name());
      responseBody.setData(Map.of("error", Map.of("message", authException.getMessage())));

      final ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(response.getOutputStream(), responseBody);
    }

}
