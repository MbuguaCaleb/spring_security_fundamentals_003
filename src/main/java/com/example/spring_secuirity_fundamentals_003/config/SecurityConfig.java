package com.example.spring_secuirity_fundamentals_003.config;


import com.example.spring_secuirity_fundamentals_003.config.security.filters.CustomAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationFilter customAuthenticationFilter;

    //Web configurer adapter has been deprecated
    //I am adding a filter at the position where the username and password that http basic relies on is
    //Note that this is the authentication filter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()//all requests must be authenticated // remember we are now not relying on the default spring boot security implementation
                .and()
                .build();

    }
}
