package mx.edu.utez.unidad3.security;

import mx.edu.utez.unidad3.security.filters.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MainSecurity {

    //Sexto paso: Registrar el filtro en MainSecurity
    @Autowired
    private JWTFilter jwtFilter;

    private final String[] SWAGGER_URLS = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "v3/api-docs.yaml",
            "/swagger-resources/**",
            "/webjars/**"
    };

    private  final String[] WHITE_LIST = {
            "/api/auth/**"
    };

    @Bean
    public SecurityFilterChain doFilterInternal(HttpSecurity http) throws Exception{
        http.csrf(c -> c.disable()).cors(c -> c.configurationSource(corsRegistry()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/client/**").hasRole("ADMIN")
                        .requestMatchers("/api/cede/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        .requestMatchers(SWAGGER_URLS).permitAll()
                        .anyRequest().authenticated()
        ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private CorsConfigurationSource corsRegistry(){
        CorsConfiguration configuration = new CorsConfiguration();
        // Que vamos a configurar
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false); //Es para cookies

        // En donde vamos a configurar
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService generateUsers() {
        //Configurar todos los usuarios
        UserDetails admin = User.builder()
                .username("superadmin")
                .password(passwordEncoder().encode("#superadmin123"))
                .roles("ADMIN")
                .build();

        UserDetails employee = User.builder()
                .username("Kevin12")
                .password(passwordEncoder().encode("#kevin123"))
                .roles("EMPLOYEE")
                .build();

        UserDetails swaggerAdmin = User.builder()
                .username("joker")
                .password(passwordEncoder().encode("#joker123"))
                .roles("DEV")
                .build();

        return new InMemoryUserDetailsManager(admin, employee, swaggerAdmin);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}