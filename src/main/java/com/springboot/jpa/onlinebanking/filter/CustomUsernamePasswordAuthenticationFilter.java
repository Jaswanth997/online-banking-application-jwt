package com.springboot.jpa.onlinebanking.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jpa.onlinebanking.entity.CustomerDetails;

public class CustomUsernamePasswordAuthenticationFilter extends  UsernamePasswordAuthenticationFilter{

	private CustomerDetails customerDetails;
	 
	 @Override
	protected String obtainUsername(HttpServletRequest request) {
		 if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			 customerDetails = null;
				try {
					CustomerDetails register = getRegisterInfo(request);
					return register.getEmailId();
				} catch (IOException e) {
					e.printStackTrace();
					return "";
				}
			}
			return super.obtainUsername(request);
	 }
	 
	 @Override
	protected String obtainPassword(HttpServletRequest request) {
		 if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
				
				try {
					CustomerDetails register = getRegisterInfo(request);
					return register.getPassword();
				} catch (Exception e) {
					e.printStackTrace();
					return "";
				}
			}
	          return super.obtainPassword(request);
	}
	 
	 @SuppressWarnings("unused")
	private CustomerDetails getRegisterInfo(HttpServletRequest request) throws IOException {
		 if(customerDetails == null) {
			 ObjectMapper mapper = new ObjectMapper();
			 String json = "";
				BufferedReader reader = request.getReader();
				while (reader.ready()) {
					json = json + reader.readLine();
				}
				customerDetails = mapper.readValue(json, CustomerDetails.class);
			}
			return customerDetails;
		 }

}//End of class
