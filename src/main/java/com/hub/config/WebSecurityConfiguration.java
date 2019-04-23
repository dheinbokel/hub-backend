package com.hub.config;

import com.hub.daos.UsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UsersRepository usersRepository;

    WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, UsersRepository usersRepository){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/content/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/likes/{contentID}", "/downloadFile/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/content/dislike/{userID}/{contentID}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/content/like/{userID}/{contentID}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/contentTags").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/tags/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/comments/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/comments/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/comments/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/comments/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/users/byID/{id}", "/users/subscription/byuserid/{userID}",
                        "/users/subscription/bytagid/{tagID}", "/users/notifications").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/users/subscription/add").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/notifications/switch").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/notifications/remove/{notificationID}", "/users/notifications/remove").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), usersRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
        //.authenticated()
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
