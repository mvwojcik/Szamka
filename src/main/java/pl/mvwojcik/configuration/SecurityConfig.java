package pl.mvwojcik.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationSuccessHandler successHandler;
    private final RestAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .withDefaultSchema()
                .dataSource(dataSource)
                .withUser("test")
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode("test"))
                .roles("USER")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilter(authenticationFilter())
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
}
