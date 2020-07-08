package com.springboot.jpa.onlinebanking.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.jpa.onlinebanking.entity.AtmSimulator;
import com.springboot.jpa.onlinebanking.entity.Beneficiary;
import com.springboot.jpa.onlinebanking.entity.Credit;
import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.entity.TransactionDetails;
import com.springboot.jpa.onlinebanking.entity.TransferFunds;


public interface CustomerDetailsService {
	
    public List<CustomerDetails> findAll();
    
    public Page<CustomerDetails> getAllCustomers(int pageNumber, int itemsPerPage);
    
    public Page<CustomerDetails> getSortedCustomers(int pageNumber, int itemsPerPage, String fieldName);
    	
	public CustomerDetails findById(int customerId);
	
	public CustomerDetails save(CustomerDetails customerDetails );
	
	public CustomerDetails update(CustomerDetails customerDetails);
	
	public void deleteById(int customerId);
	
	public CustomerDetails findByEmail(String email);

	public CustomerDetails findByAadhar(String aadhar);

	public CustomerDetails findByPhone(String mobNo);

	public CustomerDetails findByAccno(String accNo);
	
	public List<Beneficiary> myBeneficiary(int id);
	
	public String atmSimulator(int id, AtmSimulator atm);
	
	public String transferFunds(int id, TransferFunds transferFunds);
	
	public List<TransactionDetails> myTransaction(int id);
	
	public String creditAmount(int id, Credit credit);

	public List<CustomerDetails> findAllCustomers(CustomerDetails customerDetails);
	
	


}
