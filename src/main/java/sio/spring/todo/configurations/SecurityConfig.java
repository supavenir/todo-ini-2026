package sio.spring.todo.configurations;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(req -> req.requestMatchers(PathRequest.toH2Console())
				.permitAll()
				.requestMatchers("/", "/css/**", "/js/**", "/img/**", "/signin/**", "/signup/**", "/users/register/**")
				.permitAll().anyRequest().authenticated());
		http.formLogin(form -> form.loginPage("/signin").defaultSuccessUrl("/", true).permitAll());
		http.logout(logout -> logout.logoutUrl("/signout").logoutSuccessUrl("/?signout").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID"));
		http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}