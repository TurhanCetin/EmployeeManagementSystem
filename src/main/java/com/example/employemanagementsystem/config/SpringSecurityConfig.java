package com.example.employemanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                                    .antMatchers("/").permitAll()
                                    .antMatchers("/dashboard/**").hasAnyRole("admin")
                .and()
                .formLogin()
                                    .loginPage("/dashboard/login")
                                    .permitAll()
                                    .and()
                .authorizeRequests()
                                    .antMatchers("/personnel/**").hasAnyRole("user","manager")
                                    .anyRequest().authenticated()
                .and()
                .formLogin()
                                    .loginPage("/personnel/login")
                                    .permitAll()
                                    .and()
                .logout()
                                    .permitAll()
                                    .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("turhan@example").password("password").roles("user","manager")
                .and()
                .withUser("admin@example").password("password").roles("admin");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/*", "/static/*", "/css/*", "/js/*", "/images/*");
    }
}