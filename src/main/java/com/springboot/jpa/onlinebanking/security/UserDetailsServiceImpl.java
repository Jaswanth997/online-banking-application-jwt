package com.springboot.jpa.onlinebanking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springboot.jpa.onlinebanking.service.CustomerDetailsService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
		
		userDetailsImpl.setCustomerDetails(customerDetailsService.findByEmail(username));
		
		return userDetailsImpl;
		
	}  

}//End of class
