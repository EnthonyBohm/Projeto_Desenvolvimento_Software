package br.edu.ufpel.mapacultural.Config;

import br.edu.ufpel.mapacultural.Service.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final JwtAuthFilter jwtAuthFilter;
//    private final UserDetailsService userDetailsService;

//    public SecurityConfig ()
//    {
//        this.jwtAuthFilter = jwtAuthFilter;
//        this.userDetailsService = userDetailsService;
//    }

//    passwordEncoder cria as Hashes através das senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // Requisições de preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Login e autenticação
                        .requestMatchers("/auth/**").permitAll()

                        // Consultas públicas
                        .requestMatchers(HttpMethod.GET,
                                "/eventos/**",
                                "/artistas/**",
                                "/locais/**"
                        ).permitAll()

                        // Cadastro de usuário: somente visitante
                        .requestMatchers(HttpMethod.POST, "/usuarios")
                        .anonymous()

                        // Cadastro de artistas
                        .requestMatchers(HttpMethod.POST, "/artistas")
                        .hasAnyAuthority("USER", "ORGANIZADOR")

                        // Cadastro de eventos e locais
                        .requestMatchers(HttpMethod.POST,
                                "/eventos", "/locais"
                        ).hasAuthority("ORGANIZADOR")

                        // Demais cadastros bloqueados
                        .requestMatchers(HttpMethod.POST, "/**").denyAll()

                        // Demais operações exigem autenticação
                        .anyRequest().authenticated()
                )

                // Ativa a validação dos JWTs
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter( jwtAuthenticationConverter() )
                        )
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("authorities");
        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                authoritiesConverter
        );

        return converter;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UsuarioDetailsService usuarioDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(usuarioDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

}

//    //  Verifica se um usuário é valido. Recebendo o seu email e senha e validando
//    @Bean
//    public AuthenticationProvider authenticationProvider (UsuarioDetailsService usuarioDetailsService, PasswordEncoder passwordEncoder)
//    {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(usuarioDetailsService);
//        provider.setPasswordEncoder(passwordEncoder);
//
//        return provider;
//    }
