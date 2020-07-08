package com.springboot.jpa.onlinebanking.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.jpa.onlinebanking.entity.CustomerDetails;

@SuppressWarnings("serial")
@Component
public class UserDetailsImpl implements UserDetails {
 
	private CustomerDetails customerDetails;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customerDetails.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return customerDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return customerDetails.getEmailId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	//Getters & setters
	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	
	

	
}//End of class
