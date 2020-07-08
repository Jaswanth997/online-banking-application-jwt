package com.springboot.jpa.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.exceptions.EmailNotFoundException;
import com.springboot.jpa.onlinebanking.response.TokenResponse;
import com.springboot.jpa.onlinebanking.service.CustomerDetailsService;
import com.springboot.jpa.onlinebanking.util.JwtUtil;
  
@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:4200")
public class JwtController {
	
	 @Autowired
    private  CustomerDetailsService customerDetailsService;
	 
	 @Autowired
	private  AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private JwtUtil jwtUtil; 
	 
	 @Autowired
	 private UserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public TokenResponse<CustomerDetails> login(@RequestBody CustomerDetails register) throws Exception{
	
 		CustomerDetails customer =customerDetailsService.findByEmail(register.getEmailId());
		if(customer == null) {
			return new TokenResponse<CustomerDetails>(true, "Invalid Email",null,null);

		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmailId(),register.getPassword()));
		} catch(DisabledException de) {

			throw new EmailNotFoundException("User disabled");
			
		} catch(BadCredentialsException bce) {
			
			return new TokenResponse<CustomerDetails>(true, "Invalid Password",null,null);

		}// End of try catch
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(register.getEmailId());
	
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return new TokenResponse<CustomerDetails>(false, "Login Successful",jwt,customer);
	}// End of login
 	
 	
 	
}

