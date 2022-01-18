
package com.example.employemanagementsystem.security;

import com.example.employemanagementsystem.filter.CustomAuthenticationFilter;
import com.example.employemanagementsystem.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.employemanagementsystem")
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/personnel/login/");//kendi login hostumuz
        http.cors();
        http.csrf().disable();//CSRF'yi devre dışı bırakmanın nedeni, spring boot uygulamasının herkese açık olması veya geliştirme ve test aşamasındayken hantal olmasıdır.
        http.sessionManagement().sessionCreationPolicy(STATELESS);//oturum yönetimi politikası
        //http.authorizeRequests().antMatchers("/api/login/**","/api/token/refresh/**","/api/user/save").permitAll();//herkesin erişmesine izin veriyoruz
        //http.authorizeRequests().antMatchers(GET,"/api/user/**").hasAnyAuthority("ROLE_USER");
        //http.authorizeRequests().antMatchers(GET,"/api/role/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/personnel/login/**").permitAll();
        http.authorizeRequests().antMatchers("/personnel/home").hasAnyAuthority("User");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/role/create/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();//kimlik bilgilerini doğrulamasını istiyoruz
        http.formLogin().loginPage("/personnel/login").permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}