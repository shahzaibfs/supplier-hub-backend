package com.fyp.supplierHub.jwtToken;

import com.fyp.supplierHub.user.MyUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilterBefore extends OncePerRequestFilter {

    private final MyUserDetailService myUserDetailService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtRequestFilterBefore(
            MyUserDetailService myUserDetailService
            , JwtTokenUtil jwtTokenUtil)
    {
        this.myUserDetailService = myUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
    }




    @Override
    protected void doFilterInternal
            (
                    HttpServletRequest request
                    , HttpServletResponse response
                    , FilterChain filterChain
            ) throws ServletException, IOException
    {
        final String JwtHeader = request.getHeader("Authorization");
        String headerUsername =null ;
        String headerToken = null;
        System.out.println(JwtHeader);
        if(JwtHeader != null && JwtHeader.startsWith("bearer")){
            headerToken = JwtHeader.substring(7) ;
            try {
                headerUsername = jwtTokenUtil.getUsernameFromToken(headerToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }

            if(headerUsername != null && SecurityContextHolder.getContext().getAuthentication() == null){
               final  UserDetails EXISTING_USER = myUserDetailService.loadUserByUsername(headerUsername);
                System.out.println(EXISTING_USER.getUsername());
               if(jwtTokenUtil.validateToken(headerToken,EXISTING_USER)){

                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                           = new UsernamePasswordAuthenticationToken(EXISTING_USER,null , EXISTING_USER.getAuthorities());
                   usernamePasswordAuthenticationToken.setDetails(
                           new WebAuthenticationDetailsSource().buildDetails(request)
                   );

                   SecurityContextHolder.getContext().setAuthentication(
                           usernamePasswordAuthenticationToken
                   );

               }
            }

        }
        filterChain.doFilter(request,response);
    }
}
