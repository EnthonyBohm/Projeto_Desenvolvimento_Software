package br.edu.ufpel.mapacultural.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;


@Configuration
public class SecurityConfig {

    @Bean
    DaoAuthenticationProvider authenticationProvider ( UsuarioDetailsService usuarioDetailsService, PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(usuarioDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http, DaoAuthenticationProvider authProvider ) throws Exception
    {
        http
                .authenticationProvider(authProvider)
                .authorizeHttpRequests(auth ->auth
                        // Cadastro público de usuários
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        // Login e cadastro não exigem autenticação
                        .requestMatchers("/auth/**").permitAll()

                        // Qualquer outra requisição exige autenticação
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                // Retorna 401 em vez de redirecionar para uma página de login
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                )

                // Temporariamente desabilitado para facilitar os testes pela API
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}

