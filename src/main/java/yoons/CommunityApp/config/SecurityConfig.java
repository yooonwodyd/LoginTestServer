package yoons.CommunityApp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import yoons.CommunityApp.config.security.JwtAuthenticationFilter;
import yoons.CommunityApp.config.security.JwtTokenProvider;
import yoons.CommunityApp.dto.BoardPrincipal;
import yoons.CommunityApp.repository.UserRepository;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
       return http.csrf(AbstractHttpConfigurer :: disable)
               .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .httpBasic(HttpBasicConfigurer :: disable)
               .authorizeHttpRequests(authorize -> authorize
                       .requestMatchers("/loginTest/hasUser").hasAuthority("ROLE_USER")
                       .requestMatchers("/loginTest/hasAdmin").hasAuthority("ROLE_ADMIN")
                       .requestMatchers("/logintest/formLogin").permitAll()
                       .anyRequest().authenticated()
               )
               .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                       UsernamePasswordAuthenticationFilter.class)
               .build();
    }
}
