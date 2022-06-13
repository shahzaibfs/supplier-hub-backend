package com.fyp.supplierHub.config;

import com.fyp.supplierHub.jwtToken.JwtRequestFilterBefore;
import com.fyp.supplierHub.user.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailService myUserDetailService ;
    private final JwtRequestFilterBefore jwtRequestFilterBefore;
    private final PasswordEncoder passwordEncoder ;


    @Autowired
    public WebSecurityConfig(MyUserDetailService myUserDetailService, JwtRequestFilterBefore jwtRequestFilterBefore, PasswordEncoder passwordEncoder) {
        this.myUserDetailService = myUserDetailService;
        this.jwtRequestFilterBefore = jwtRequestFilterBefore;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/swagger-ui/**",
                        "/v2/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**",
                        "favicon.ico")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1.0/category/**").permitAll()
                .antMatchers("/api/v1.0/order").hasAuthority("ROLE_CUSTOMER")
                .antMatchers("/api/v1.0/order-supplier/**").hasAuthority("ROLE_SUPPLIER")
                .antMatchers("/api/v1.0/cart").hasAuthority("ROLE_CUSTOMER")
                .antMatchers("/api/v1.0/product-category/**","/api/v1.0/upload/**","/image/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1.0/product/**").permitAll()
                .antMatchers("/api/v1.0/product/**").permitAll()
                .antMatchers("/api/v1.0/supplier-public/**").permitAll()
                .antMatchers("/api/v1.0/supplier").hasAuthority("ROLE_SUPPLIER")
                .antMatchers("/api/v1.0/customer/**").hasAuthority("ROLE_CUSTOMER")
                .antMatchers("api/v1.0/customer-shipping-address/**").hasAuthority("ROLE_CUSTOMER")
                .antMatchers("/api/v1.0/product-supplier/**").hasAuthority("ROLE_SUPPLIER")
                .antMatchers(HttpMethod.GET,"/api/v1.0/user")
                .hasAnyAuthority("ROLE_CUSTOMER","ROLE_SUPPLIER")
                .antMatchers(
                        "/api/v1.0/authenticate",
                        "/api/v1.0/user/**",
                        "/api/v1.0/roles/**").permitAll()

                .anyRequest()

                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilterBefore, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder);
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
