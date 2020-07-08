package com.springboot.jpa.onlinebanking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.jpa.onlinebanking.filter.JwtRequestFilter;
import com.springboot.jpa.onlinebanking.security.BootAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OnlineBankingApplicationJWTConfigurer extends WebSecurityConfigurerAdapter {

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Autowired
	private BootAuthenticationEntryPoint  bootAuthenticationEntryPoint;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
	} // End of configureGlobal()
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
		    .authorizeRequests().antMatchers("/api/login","/api/register","/api/forgotPassword/{emailId}/{password}").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api/customer/{pageNumber}/{itemsPerPage}"
		    		,"/api/customer/{pageNumber}/{itemsPerPage}/{fieldName}"
		    		,"/api/updateCustomer","/api/update-success/{id}","/api/update-rejected/{id}","/api/update-inprogress/{id}"
		    		,"/api/addCustomer", "/api/post-news"
		    		,"/api/allTransactions","api/allTransactions/{pageNumber}/{itemsPerPage}"
            		,"/api/allTransactions/{pageNumber}/{itemsPerPage}/{fieldName}"
            		,"/api/completedRequests/{pageNumber}/{itemsPerPage}"
            		,"api/completedRequests/{pageNumber}/{itemsPerPage}/{fieldName}").hasRole("ADMIN")
		    .and()
            .authorizeRequests().antMatchers("/api/beneficiary"
            		,"/api/beneficiary/{pageNumber}/{itemsPerPage}"
            		,"/api/beneficiary/{pageNumber}/{itemsPerPage}/{fieldName}"
            		,"/api/beneficiary/{beneficiaryId}"
            		,"/api/deleteBeneficiary/{beneficiaryId}"
            		,"/api/addBeneficiary/{id}", "/api/credit/{id}","/api/atm/{id}"
            		,"/api/transfer/{id}","/api/myTransaction/{id}","/api/myBeneficiary/{id}"
            		,"/api/get-news"
            		,"/api/get-news/{pageNumber}/{itemsPerPage}"
            		,"/api/get-news/{pageNumber}/{itemsPerPage}/{fieldName}"
            		,"/api/myRequest/{id}","/api/send-request/{id}"
            		,"/api/customer/{id}").hasRole("USER")
            .and()
            .authorizeRequests().antMatchers("/api/requests","/api/requests/{pageNumber}/{itemsPerPage}"
             		,"/api/requests/{pageNumber}/{itemsPerPage}/{fieldName}"
            		,"/api/requests/{requestId}"
            		).hasAnyRole("USER","ADMIN")
		    .anyRequest().authenticated()
		    .and()
		    .exceptionHandling().authenticationEntryPoint(bootAuthenticationEntryPoint)
		    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}// End of configure()
	                               
}// End of class
