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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.entity.PostNews;
import com.springboot.jpa.onlinebanking.entity.Request;
import com.springboot.jpa.onlinebanking.entity.TransactionDetails;
import com.springboot.jpa.onlinebanking.response.Response;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/customer")
	public List<CustomerDetails> findAll(){
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/customer", HttpMethod.GET, entity, List.class).getBody();
	}
	
	@GetMapping("/customer/{pageNumber}/{itemsPerPage}")
	public String getAllCustomers(@PathVariable int pageNumber, @PathVariable int itemsPerPage){
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/customer/"+pageNumber+"/"+itemsPerPage, HttpMethod.GET, entity, String.class).getBody();
		
	}
	
	@GetMapping("/customer/{pageNumber}/{itemsPerPage}/{fieldName}")
	public String getSortedCustomers(@PathVariable int pageNumber, @PathVariable int itemsPerPage, @PathVariable String fieldName){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/customer/"+pageNumber + "/"+itemsPerPage+"/"+fieldName, HttpMethod.GET, entity, String.class).getBody();
		
	}
	@PostMapping("/addCustomer")
	public Response<CustomerDetails> addCustomer(@Valid @RequestBody CustomerDetails customerDetails) {
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<CustomerDetails> entity = new HttpEntity<CustomerDetails>(customerDetails,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/addCustomer", HttpMethod.POST, entity, Response.class).getBody();
	
	}
	
	@PutMapping("/updateCustomer")
	public Response<CustomerDetails> updateCustomer(@RequestBody CustomerDetails customer) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<CustomerDetails> entity = new HttpEntity<CustomerDetails>(customer,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/updateCustomer", HttpMethod.PUT, entity, Response.class).getBody();
	}
	
	@PutMapping("/forgotPassword/{emailId}/{password}")
	public Response<CustomerDetails> forgotPassword(@PathVariable String emailId,@PathVariable String password,@RequestBody CustomerDetails theRequestForm) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	      HttpEntity<CustomerDetails> entity = new HttpEntity<CustomerDetails>(theRequestForm,headers);
	      
	      return restTemplate.exchange("http://localhost:8081/api/forgotPassword/" + emailId + "/" + password, HttpMethod.PUT, entity, Response.class).getBody();
	   }
	
	
	@PostMapping("/post-news")
	public Response<PostNews> savePost(@Valid @RequestBody PostNews postNews) {

		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<PostNews> entity = new HttpEntity<PostNews>(postNews,headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/post-news", HttpMethod.POST, entity, Response.class).getBody();

	}
	
	@GetMapping("/requests")
	public List<Request> findAllRequests(){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/requests", HttpMethod.GET, entity, List.class).getBody();
	}
	
	@GetMapping("/requests/{pageNumber}/{itemsPerPage}")
	public String getRequests(@PathVariable int pageNumber, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/requests/"+pageNumber+"/"+itemsPerPage, HttpMethod.GET, entity, String.class).getBody();
		
	}
	
	@GetMapping("/requests/{pageNumber}/{itemsPerPage}/{fieldName}")
	public String getSortRequests(@PathVariable int pageNumber, @PathVariable int itemsPerPage, @PathVariable String fieldName){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/requests/"+pageNumber + "/"+itemsPerPage+"/"+fieldName, HttpMethod.GET, entity, String.class).getBody();
		
	}
	@GetMapping("/completedRequests/{pageNumber}/{itemsPerPage}")
	public String getAllRequests(@PathVariable int pageNumber, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/completedRequests/"+pageNumber+"/"+itemsPerPage, HttpMethod.GET, entity, String.class).getBody();
		
	}
	
	@GetMapping("/completedRequests/{pageNumber}/{itemsPerPage}/{fieldName}")
	public String getSortedRequests(@PathVariable int pageNumber, @PathVariable int itemsPerPage, @PathVariable String fieldName){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/completedRequests/"+pageNumber + "/"+itemsPerPage+"/"+fieldName, HttpMethod.GET, entity, String.class).getBody();
		
	}
	
	@PutMapping("/update-success/{id}")
	public Response<Request> findAllSuccess(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/update-success/"+id, HttpMethod.PUT, entity, Response.class).getBody();
	}
	@PutMapping("/update-rejected/{id}")
	public Response<Request> findAllChanged(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/update-rejected/"+id, HttpMethod.PUT, entity, Response.class).getBody();
	}
	
	@PutMapping("/update-inprogress/{id}")
	public Response<Request> findAllInProgress(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/update-inprogress/"+id, HttpMethod.PUT, entity, Response.class).getBody();
	}

	@GetMapping("/allTransactions")
	public List<TransactionDetails> findAllTranscations() {
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/allTransactions", HttpMethod.GET, entity, List.class).getBody();
	}

	@GetMapping("/allTransactions/{pageNumber}/{itemsPerPage}")
	public String getAllTransactions(@PathVariable int pageNumber, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/allTransactions/"+pageNumber+"/"+itemsPerPage, HttpMethod.GET, entity, String.class).getBody();
		
	}
	
	@GetMapping("/allTransactions/{pageNumber}/{itemsPerPage}/{fieldName}")
	public String getSortTransactions(@PathVariable int pageNumber, @PathVariable int itemsPerPage, @PathVariable String fieldName){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      return restTemplate.exchange(
	    	         "http://localhost:8081/api/allTransactions/"+pageNumber + "/"+itemsPerPage+"/"+fieldName, HttpMethod.GET, entity, String.class).getBody();
		
	}
}
