/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/webjars/**", "/", "/login").permitAll()
                .requestMatchers("/roles/**", "/usuarios/**").hasRole("ADMIN")
                .requestMatchers("/eventos/nuevo", "/eventos/guardar", "/eventos/editar/**", "/eventos/eliminar/**")
                    .hasAnyRole("ADMIN", "ORGANIZADOR")
                .requestMatchers("/eventos/**", "/consultas/**")
                    .hasAnyRole("ADMIN", "ORGANIZADOR", "CLIENTE")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successHandler())
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/acceso_denegado"))
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                org.springframework.security.core.Authentication authentication)
                    throws IOException, ServletException {

                boolean esAdmin = authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

                boolean esOrganizador = authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ORGANIZADOR"));

                boolean esCliente = authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));

                if (esAdmin) {
                    response.sendRedirect("/usuarios");
                } else if (esOrganizador) {
                    response.sendRedirect("/eventos");
                } else if (esCliente) {
                    response.sendRedirect("/eventos");
                } else {
                    response.sendRedirect("/");
                }
            }
        };
    }
}