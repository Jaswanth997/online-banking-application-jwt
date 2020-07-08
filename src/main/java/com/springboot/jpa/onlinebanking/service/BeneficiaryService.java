package com.springboot.jpa.onlinebanking.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.jpa.onlinebanking.entity.Beneficiary;

public interface BeneficiaryService {
	
	public List<Beneficiary> findAll();
	
	public Page<Beneficiary> getAllBeneficiaries(int pageNumber, int itemsPerPage);
	    
	public Page<Beneficiary> getSortBeneficiaries(int pageNumber, int itemsPerPage, String fieldName);
	
    public Beneficiary findById(int beneficiaryId);
    
    public Beneficiary findByFirstName(String firstName);
    
    public Beneficiary findByLastName(String lastName);
    
    public Beneficiary findByEmail(String emailId);
    
    public Beneficiary findByAccountNumber(String accountNumber);
    
    public Beneficiary findByPhoneNumber(String phoneNumber);
    
	
	public Beneficiary save(Beneficiary beneficiary );
	
	public String addBeneficiary(int id, Beneficiary beneficiary);
	
	public void deleteById(int beneficiaryId);

	public List<Beneficiary> findAllBeneficiaries(Beneficiary beneficiary);

}
