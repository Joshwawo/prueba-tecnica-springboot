package com.example.crudspring.middlewares;
import com.example.crudspring.helpers.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JwtAuthFilter{






    //private final JwtTokenUtil jwtTokenUtil;
    /*
    public JwtAuthFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request);
            if(token != null && jwtTokenUtil.validateToken(token)){
                String username = jwtTokenUtil.getUsernameFromToken(token);
                request.setAttribute("username", username);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    */
}
