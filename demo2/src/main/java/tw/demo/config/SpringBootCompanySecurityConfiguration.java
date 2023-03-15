package tw.demo.config;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(2)
public class SpringBootCompanySecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	@Qualifier("companyDetailsService")
	UserDetailsService companyDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(companyDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.antMatcher("/company/**")
		.authorizeRequests().anyRequest().authenticated()
		.and().formLogin().loginPage("/company/login")
			.defaultSuccessUrl("/company/dashboard", true)
			.failureUrl("/company/accessdenied")
		.permitAll()
		.and().rememberMe().tokenValiditySeconds(86400).key("user-company")
		.and().logout().logoutUrl("/company/logout")
		.addLogoutHandler((request, response, auth) -> {
            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();
                Cookie cookieToDelete = new Cookie(cookieName, null);
                cookieToDelete.setMaxAge(0);
                response.addCookie(cookieToDelete);
            }
        })
		.logoutSuccessUrl("/homepage")
		.and().exceptionHandling().accessDeniedPage("/company/accessdenied");
	http.csrf().disable();
	}	
}
