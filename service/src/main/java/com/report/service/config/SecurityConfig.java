package com.report.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@Slf4j
public class WebSecurityConfiguration {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        log.info("Configuring securityFilterChain...");
//        return http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/**").authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling.authenticationEntryPoint(new RestAuthenticationEntryPoint())
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginProcessingUrl("/api/login")
//                                .successHandler(new LoginSuccessHandler())
//                                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                )
//                .csrf(csrf ->
//                        csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )
//                .build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        log.info("Configuring userDetailsService...");
//        String password = "password";
//        String username = "user";
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        String encodedPassword = passwordEncoder().encode(password);
//        manager.createUser(User.withUsername(username).password(encodedPassword).authorities("read").roles("USER").build());
//        return manager;
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((authorize) -> authorize
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());

    return http.build();
}

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Configuring passwordEncoder...");
        return new BCryptPasswordEncoder();
    }

}