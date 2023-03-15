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
@Order(1)
public class SpringBootAdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserDetailsService adminDetailsServiceImpl;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/admin/**")
			.authorizeRequests().anyRequest().authenticated()
			.and().formLogin().loginPage("/admin/login")
				.defaultSuccessUrl("/admin/dashboard", true)
				.failureUrl("/admin/accessdenied")
			.permitAll()
			.and().logout().logoutUrl("/admin/logout")
			.addLogoutHandler((request, response, auth) -> {
                for (Cookie cookie : request.getCookies()) {
                    String cookieName = cookie.getName();
                    Cookie cookieToDelete = new Cookie(cookieName, null);
                    cookieToDelete.setMaxAge(0);
                    response.addCookie(cookieToDelete);
                }
            }).logoutSuccessUrl("/homepage")
			.and().exceptionHandling().accessDeniedPage("/admin/accessdenied");
		http.csrf().disable();
	}
}	