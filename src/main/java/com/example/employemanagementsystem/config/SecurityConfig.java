package com.example.employemanagementsystem.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() throws Exception {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(encoder().encode("user")).roles("User").build());
        manager.createUser(User.withUsername("admin").password(encoder().encode("admin")).roles("Admin").build());
        manager.createUser(User.withUsername("manager").password(encoder().encode("manager")).roles("Manager").build());
        return manager;
    }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private MyAccessDeniedHandler myAccessDeniedHandler;
        public App1ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("admin")).roles("Admin");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/dashboard*").authorizeRequests().anyRequest().hasRole("Admin")
                    // log in
                    .and().formLogin().loginPage("/").loginProcessingUrl("/dashboard/login").failureUrl("/failed").defaultSuccessUrl("/dashboard/home")
                    // logout
                    .and().logout().logoutUrl("/admin_logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).and().csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private MyAccessDeniedHandler myAccessDeniedHandler;
        public App2ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("user").password(encoder().encode("user")).roles("User");
        }

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/personnel*").authorizeRequests().anyRequest().hasAnyRole("User","Manager")
                    // log in
                    .and().formLogin().loginPage("/personnelLogin").loginProcessingUrl("/personnel/login").failureUrl("/failed").defaultSuccessUrl("/personnel/home")
                    // logout
                    .and().logout().logoutUrl("/user_logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).and().csrf().disable();
        }
    }

}
