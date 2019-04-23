package com.hub.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    JWTAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{

        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        System.out.println("Header initialized");
        if(header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)){

            UsernamePasswordAuthenticationToken authenticationToken = getAuthorization(req);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(req, res);
        System.out.println("chain.doFilter worked");
    }

    private UsernamePasswordAuthenticationToken getAuthorization(HttpServletRequest request){

        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if(token != null){
            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getSubject();

            if(user != null){
                DecodedJWT jwt = JWT.decode(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

                String role = jwt.getClaim(SecurityConstants.AUTHORITIES_KEY).asString();
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));

                System.out.println("Username and password stuff");
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
            return null;
        }
        return null;
    }
}
