package tw.demo.config;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(3)
public class SpringBootUserSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserDetailsService userDetailsServiceImpl;
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/oauth2/**").permitAll()
			.antMatchers("/user/**")
			.authenticated().anyRequest()
			.permitAll()
			.and().formLogin().loginPage("/user/login")
				.defaultSuccessUrl("/user/homepage", true)
				.failureUrl("/user/accessdenied")
			.permitAll()
			.and().rememberMe().tokenValiditySeconds(86400).key("user-member")
			.and()
			.oauth2Login()
				.loginPage("/user/login")
//				.userInfoEndpoint()
//				.userService(oAuth2UserService)
//				.and().permitAll()
				.defaultSuccessUrl("/user/Oauth2homepage", true)
			.and().logout().logoutUrl("/user/logout")
			.addLogoutHandler((request, response, auth) -> {
                for (Cookie cookie : request.getCookies()) {
                    String cookieName = cookie.getName();
                    Cookie cookieToDelete = new Cookie(cookieName, null);
                    cookieToDelete.setMaxAge(0);
                    response.addCookie(cookieToDelete);
                }
            })
			.logoutSuccessUrl("/homepage")
			.and().exceptionHandling().accessDeniedPage("/user/accessdenied");
		
		http.csrf().disable();
	}
	
	
}
