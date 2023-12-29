package com.example.spring_secuirity_fundamentals_003.config.security.providers;


import com.example.spring_secuirity_fundamentals_003.config.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${our.very.very.secret.key}")
    private String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthentication ca = (CustomAuthentication) authentication;

        String headerKey = ca.getKey();

        //if value of key provided in the headers is correct, then the authentication will be true
        if(key.equals(headerKey)){
            return new CustomAuthentication(true,null);
        }

       throw new BadCredentialsException("Oh No");

    }


    //Here is where we inform the provider which authentication to use
    //Note we can have more than one authentication
    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
