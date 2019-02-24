package com.hub.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    WebSecurityConfiguration(){

    }

    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll();
    }
}
