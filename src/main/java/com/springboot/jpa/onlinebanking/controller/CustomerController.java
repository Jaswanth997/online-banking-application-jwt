package com.springboot.jpa.onlinebanking.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.jpa.onlinebanking.entity.AtmSimulator;
import com.springboot.jpa.onlinebanking.entity.Beneficiary;
import com.springboot.jpa.onlinebanking.entity.Credit;
import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.entity.PostNews;
import com.springboot.jpa.onlinebanking.entity.Request;
import com.springboot.jpa.onlinebanking.entity.TransactionDetails;
import com.springboot.jpa.onlinebanking.entity.TransferFunds;
import com.springboot.jpa.onlinebanking.response.Response;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private RestTemplate restTemplate;
	

	@GetMapping("/myTransaction/{id}")
 	public List<TransactionDetails> myTransaction(@PathVariable int id) {
          HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/myTransaction/"+id, HttpMethod.GET, entity, List.class).getBody();
 	}

 	@GetMapping("/myBeneficiary/{id}")
 	public List<Beneficiary> myBeneficiary(@PathVariable int id) {

 		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/myBeneficiary/"+id, HttpMethod.GET, entity, List.class).getBody();
 	}
	
	@GetMapping("/customer/{id}")

	public Response<CustomerDetails> getCustomerDetails(@PathVariable int id) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/customer/"+id, HttpMethod.GET, entity, Response.class).getBody();

	}
	
	@PostMapping("/atm/{id}")
 	public Response<AtmSimulator> atm(@PathVariable int id, @Valid @RequestBody AtmSimulator atm) {

		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<AtmSimulator> entity = new HttpEntity<AtmSimulator>(atm,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/atm/"+id, HttpMethod.POST, entity, Response.class).getBody();
 	}

	@PostMapping("/transfer/{id}")
 	public Response<TransferFunds> transferFunds(@PathVariable int id,@Valid  @RequestBody TransferFunds transferFunds) {

		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<TransferFunds> entity = new HttpEntity<TransferFunds>(transferFunds,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/transfer/"+id, HttpMethod.POST, entity, Response.class).getBody();
 	}

   @PostMapping("/credit/{id}")
 	public Response<Credit> transferFunds(@PathVariable int id,@Valid  @RequestBody Credit credit) {

 		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<Credit> entity = new HttpEntity<Credit>(credit,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/credit/"+id, HttpMethod.POST, entity, Response.class).getBody();
 	
   }
   
   @DeleteMapping ("/deleteBeneficiary/{beneficiaryId}")
   public Response<Beneficiary> deleteBeneficiary(@PathVariable int beneficiaryId) {
	   HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/deleteBeneficiary/"+beneficiaryId, HttpMethod.DELETE, entity, Response.class).getBody();
}
    
    @PostMapping("/addBeneficiary/{id}")
	public Response<Beneficiary> addBeneficiary(@PathVariable int id, @Valid @RequestBody Beneficiary beneficiary) {
   	 
    	HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<Beneficiary> entity = new HttpEntity<Beneficiary>(beneficiary,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/addBeneficiary/"+id, HttpMethod.POST, entity, Response.class).getBody();
	}

    @GetMapping("/get-news")
	public Response<List<PostNews>> findNews() {
    	HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/get-news", HttpMethod.GET, entity, Response.class).getBody();
	}
	
	@GetMapping("/myRequest/{id}")
 	public List<Request> myRequest(@PathVariable int id) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/myRequest/"+id, HttpMethod.GET, entity, List.class).getBody();
 	}
	
	@PostMapping("/send-request/{id}")
	public Response<Request> addRequest(@PathVariable int id, @Valid @RequestBody Request request) {
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<Request> entity = new HttpEntity<Request>(request,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8082/api/send-request/"+id, HttpMethod.POST, entity, Response.class).getBody();
	
    }
}
