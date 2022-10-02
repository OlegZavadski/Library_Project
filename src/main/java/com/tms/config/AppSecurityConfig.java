package com.tms.config;

import com.tms.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl clientDetailsService;

    public AppSecurityConfig(UserDetailsServiceImpl clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/user/**")
                .hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/process_login")
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/");
                })
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/login");
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler((request, response, authentication) -> {
                    request.getSession().invalidate();
                    try {
                        response.sendRedirect("/");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
