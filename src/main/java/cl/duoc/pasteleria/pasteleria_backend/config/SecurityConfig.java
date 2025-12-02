package cl.duoc.pasteleria.pasteleria_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())   // desactivamos CSRF para probar fácil
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()   // PERMITIR TODO (después pondremos reglas)
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // para H2

        return http.build();
    }
}