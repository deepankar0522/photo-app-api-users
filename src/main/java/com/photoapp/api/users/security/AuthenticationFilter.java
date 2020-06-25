package com.photoapp.api.users.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.photoapp.api.users.service.UserService;
import com.photoapp.api.users.shared.dto.UserDto;
import com.photoapp.api.users.ui.model.LoginRequestModel;
import com.photoapp.api.users.ui.model.LoginResponseModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment environment;

    public AuthenticationFilter(UserService usersService, Environment environment, AuthenticationManager authenticationManager) {
        this.userService = usersService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            LoginRequestModel credentials = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
            System.out.println("User = "+credentials.getEmail() + " : " + credentials.getPassword());
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        String userName = ((User) auth.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserDetailsByEmail(userName);
        String token = Jwts.builder().setSubject(userDetails.getUserId()).setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time")))).signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();
        System.out.println("token: " + token);
        Gson gson = new Gson();
        ModelMapper modelMapper = new ModelMapper();
        LoginResponseModel loginResponseModel = modelMapper.map(userDetails, LoginResponseModel.class);
        loginResponseModel.setToken(token);
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.addHeader("token", token);
        res.addHeader("userId", userDetails.getUserId());
        out.print(gson.toJson(loginResponseModel));
        out.flush();
    }

}
