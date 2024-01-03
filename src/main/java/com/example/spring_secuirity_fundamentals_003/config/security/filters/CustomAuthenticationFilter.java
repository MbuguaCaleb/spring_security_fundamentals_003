package com.example.spring_secuirity_fundamentals_003.config.security.filters;

import com.example.spring_secuirity_fundamentals_003.config.security.authentication.CustomAuthentication;
import com.example.spring_secuirity_fundamentals_003.config.security.managers.CustomAuthenticationManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//for a filter to be called only once, i must extend the once per request filter
//we will propagate to the next filter in the chain only when the request works

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    //It is the filter that takes the reposibility to call the manager,
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //pseudocode
        //1.create an authentication object which is not yet authenticated
        //2.delegate the authentication object to the manager
        //3.get back the authentication from the manager
        //4.if the object is authenticated,send the request to the next filter in the chain


        //getting the key and creating an authentication
        String key = request.getHeader("key");
        CustomAuthentication customAuthentication = new CustomAuthentication(false, key);

        //takes in an authentication
        //manager calls the provider //provider should be able to change the state of authentication
        Authentication authenticateUser = customAuthenticationManager.authenticate(customAuthentication);

        if (authenticateUser.isAuthenticated()) {
            //add the authenticated user to the security context,so that it can be used by the authorization filter
            //authorization heavily relies on the security object
            SecurityContextHolder.getContext().setAuthentication(authenticateUser);
            filterChain.doFilter(request, response); //only when authentication works
        }

    }
}
