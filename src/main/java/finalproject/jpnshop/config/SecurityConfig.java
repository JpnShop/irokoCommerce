package finalproject.jpnshop.config;

import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.config.auth.jwt.JwtAuthenticationFilter;
import finalproject.jpnshop.config.auth.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final MemberRepository memberRepository;
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .apply(new MyCustomDsl())
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/members").hasRole("USER")
            .antMatchers(HttpMethod.PUT, "/members").hasRole("USER")
            .antMatchers("/mypage/**")
            .access("hasRole('ROLE_USER')")
            .antMatchers(HttpMethod.POST, "/customers/notices").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/customers/notices/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/customers/notices/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/customers/reviews/**").hasRole("USER")
            .antMatchers(HttpMethod.PUT, "/customers/reviews/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
            .antMatchers(HttpMethod.DELETE, "/customers/reviews/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

            .antMatchers(HttpMethod.POST, "/customers/questions/**").hasRole("USER")
            .antMatchers(HttpMethod.PUT, "/customers/questions/**").hasRole("USER")
            .antMatchers(HttpMethod.DELETE, "/customers/questions/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

            .antMatchers(HttpMethod.POST, "/customers/answers/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/customers/answers/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/customers/answers/**").hasRole("ADMIN")

            .antMatchers("/favorites/**").access("hasRole('USER')")

            .antMatchers("/api/v1/admin/**")
            .access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll();

    return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                .addFilter(corsFilter)
                .addFilter(new JwtAuthenticationFilter(authenticationManager))
                .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository));

        }
    }

}
