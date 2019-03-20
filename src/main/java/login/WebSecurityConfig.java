package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import login.service.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(getPasswordEncoder());
		
		
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/products").hasRole("ADMIN")
                .antMatchers("/resources/**", "/static/**","/webjars/**", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
	private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
            	
            	if (charSequence.equals(encode(s)))
            	{
            		return true;
            	}
            	else
            	{
            		return false;
            	}
            	
            	 
            }
        };
    }
	
	@Bean
	@Override
    public CustomUserDetailsService userDetailsService() {
		
        return userDetailsService;
    }
	
}
