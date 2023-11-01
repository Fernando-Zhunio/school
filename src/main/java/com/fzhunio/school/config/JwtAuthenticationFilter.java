package com.fzhunio.school.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzhunio.school.user.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        UserEntity user = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username = user.getUsername();
            password = user.getPassword();
        } catch (Exception e) {
            // TODO: handle exception
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        String token = jwtUtils.generateToken(user.getUsername());

        response.addHeader("Authorization", token);
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("user", user);
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authentication);
    }

}

