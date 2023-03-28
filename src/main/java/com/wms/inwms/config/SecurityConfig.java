package com.wms.inwms.config;

import com.wms.inwms.domain.user.UserService;
import com.wms.inwms.security.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.valves.rewrite.RewriteValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addContextValves(new RewriteValve());
//    }

    private final TokenProperties tokenProperties;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        return http.csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()// 예외처리 기능 동작
                //인증 인가 예외 처리 추상을 받아서 하는경우도 있음
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                //.authenticationEntryPoint(jwtAccessDeniedHandler)
                .and()
                .logout()
                .and()
                //설명상..UsernamePasswordAuthenticationFilter 전에 jwtAuthenticationFilter(커스텀 필터) 추가한다고 명시해논 config라고 한다..음
                .addFilterBefore(jwtAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class) //커스텀 필터 추가..UsernamePasswordAuthenticationFilter 먼저 실행
                //설명상.. 알려진 클래스 Filter중 하나 뒤에 추가할 수 있다..그럼 위 FilterBefor와 같이 JwtAuthenticationFilter 필터 뒤에 AuthorizationFilter가 추가되는거다..
                .addFilterAfter(new AuthorizationFilter(tokenProperties), JwtAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/").permitAll().antMatchers("/static/**").permitAll()
                .anyRequest().authenticated().and().build();

//        return http
//                .cors()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .build();

        //antMatchers 특정 리소스에 대해서 권한을 설정
        //permitAll antMatchers 설정한 리소스의 접근을 인증절차 없이 허용한다는 의미
        //anyRequest 모든 리소스를 의미하며 접근허용 리소스 및 인증후 특정 레벨의 권한을 가진 사용자만 접근가능한 리소스를 설정하고 그외 나머지 리소스들은 무조건 인증을 완료해야 접근이 가능

    }

//    @Bean WebSecurityConfigurerAdapter.AuthenticationManagerDelegator()
//    public WebSecurityCustomizer webSecurityCustomizer(AuthenticationManagerBuilder auth) throws Exception {
//        return auth.authenticationProvider(new JwtAuthenticationProvider(passwordEncoder));
//    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        //return authenticationConfiguration.getAuthenticationManager();

        return new ProviderManager(new JwtAuthenticationProvider(bCryptPasswordEncoder, customUserDetailsService));
    }


//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        return builder.userDetailsService(userDetailsService).passwordEncoder(encoder()).and().build();
//    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        return builder.authenticationProvider(new JwtAuthenticationProvider(null)).build();
//    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenProperties.getLoginPath());
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager); // 확인필요
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler(tokenProperties, userService));
        return jwtAuthenticationFilter;
    }

}
