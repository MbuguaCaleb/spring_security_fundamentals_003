package com.example.spring_secuirity_fundamentals_003.config.security.managers;

import com.example.spring_secuirity_fundamentals_003.config.security.providers.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    //remember the manager delegates the authentication logic to the provider

    private final CustomAuthenticationProvider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //will call my provider
        if(provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }

        throw new BadCredentialsException("Oh No");

    }
}
