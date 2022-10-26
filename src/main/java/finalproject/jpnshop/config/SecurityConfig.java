package finalproject.jpnshop.config;

import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.jwt.JwtAccessDeniedHandler;
import finalproject.jpnshop.jwt.JwtAuthenticationEntryPoint;
import finalproject.jpnshop.jwt.JwtFilter;
import finalproject.jpnshop.jwt.TokenProvider;
import finalproject.jpnshop.oauth2.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomUserDetailService customUserDetailService;

    private String[] swaggerList = {
/* swagger v2 */
        "/v2/api-docs",
        "/v2/**",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        /* swagger v3 */
        "/v3/api-docs/**",
        "/swagger-ui/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http
            .httpBasic().disable()
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
            .authorizeRequests()
            .antMatchers(swaggerList).permitAll()
            .antMatchers(HttpMethod.GET, "/members/{username}").hasRole("USER")
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
            .and()
            .authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .apply(new JwtSecurityConfig(tokenProvider))
            .and()
            .oauth2Login()
            .userInfoEndpoint().userService(customUserDetailService)
            .and()
            .defaultSuccessUrl("/auth/Glogin")
            .failureUrl("/fail");

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("refreshToken");
        source.registerCorsConfiguration("/**",config);
        return source;
    }

}
