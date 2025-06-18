package springweb.aiGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF保护（API服务通常不需要）
                .csrf(AbstractHttpConfigurer::disable)

                // 授权配置（使用新式Lambda DSL）
                .authorizeHttpRequests(auth -> auth
                        // 允许无需认证的端点
                        .requestMatchers("/api/users/register", "/api/users/login","/api/users/change-password","/api/questions","/api/questions/**","/error").permitAll()
                        // 其他所有请求需要认证
                        .anyRequest().authenticated()
                )

                // 禁用表单登录（纯API服务不需要）
                .formLogin(AbstractHttpConfigurer::disable)

                // 禁用HTTP基本认证
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}