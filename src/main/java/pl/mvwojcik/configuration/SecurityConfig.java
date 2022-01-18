package pl.mvwojcik.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.mvwojcik.security.filter.JsonObjectAuthenticationFilter;
import pl.mvwojcik.security.filter.JwtAuthorizationFilter;
import pl.mvwojcik.security.service.MyUserDetailsService;
import pl.mvwojcik.security.handler.RestAuthenticationFailureHandler;
import pl.mvwojcik.security.handler.RestAuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationSuccessHandler successHandler;
    private final RestAuthenticationFailureHandler failureHandler;
    private final MyUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/time/**").permitAll()
                .antMatchers(HttpMethod.GET,"/allergens/**").permitAll()
                .antMatchers(HttpMethod.POST,"/allergens/**").hasAuthority("ROLE_USER")
                .antMatchers("/allergens/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/ingredients/**").permitAll()
                .antMatchers(HttpMethod.POST,"/ingredients/**").hasAuthority("ROLE_USER")
                .antMatchers("/ingredients/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/recipes/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers(HttpMethod.POST,"/recipes/**").hasAuthority("ROLE_USER")
                .antMatchers("/recipes/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/diets/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers(HttpMethod.POST,"/diets/**").hasAuthority("ROLE_USER")
                .antMatchers("/diets/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService, secret))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .headers().frameOptions().disable();
    }


    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter jsonObjectAuthenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
        jsonObjectAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        jsonObjectAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        jsonObjectAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        return jsonObjectAuthenticationFilter;
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    @Primary
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
