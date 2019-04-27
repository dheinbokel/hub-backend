package com.hub.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hub.daos.UsersRepository;
import com.hub.models.HubUser;
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
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private UsersRepository usersRepository;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager, UsersRepository usersRepository){
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try{
            HubUser credentials = new ObjectMapper().readValue(req.getInputStream(), HubUser.class);
            System.out.println("attemptAuthentication()");
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUserName(), credentials.getPassword(), new ArrayList<>()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authentication) throws IOException, ServletException{

        User user = (User) authentication.getPrincipal();

        System.out.println(".getPrinciple worked");
        HubUser hubUser = usersRepository.findByUserName(user.getUsername());

        if(!hubUser.isActive()){
            //Do Nothing
        }
        else {

            String role = hubUser.getPrmID() == 1 ? "ADMIN" : "USER";

            System.out.println("Role set");

            String token = JWT.create().withSubject(hubUser.getUserName())
                    .withNotBefore(new Date())
                    .withClaim("userName", hubUser.getUserName())
                    .withClaim("userID", hubUser.getUserID())
                    .withClaim("email", hubUser.getEmail())
                    .withClaim("fName", hubUser.getfName())
                    .withClaim("lName", hubUser.getlName())
                    .withClaim("dptID", hubUser.getDptID())
                    .withClaim("frID", hubUser.getFrID())
                    .withClaim("prmID", hubUser.getPrmID())
                    .withClaim(SecurityConstants.AUTHORITIES_KEY, role)
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPERATION_TIME))
                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

            res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
            res.addHeader("Access-Control-Expose-Headers", "Authorization");

            res.getWriter().write("{\"" + SecurityConstants.HEADER_STRING + "\":\"" + token + "\"}");

            System.out.println("Token created");
        }
    }
}
