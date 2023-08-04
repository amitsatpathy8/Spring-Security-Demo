package com.employee.SpringBootSecurityEmployee.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class Config {

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email, epassword, active FROM employee WHERE email = ?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, role FROM roles WHERE email = ?");
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public UserDetailsService userDetails(){
        return new EmployeeIntoUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetails());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configure -> configure
                        .requestMatchers(HttpMethod.GET, "/employee").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/employee").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/employee").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employee/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/employee/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/employee/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/manager").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/manager").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/manager/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/manager/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/manager/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/manager/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails admin = User.builder().username("admin").password("{noop}admin").roles("EMPLOYEE", "MANAGER", "ADMIN").build();
//        return new InMemoryUserDetailsManager(admin);
//    }
}
