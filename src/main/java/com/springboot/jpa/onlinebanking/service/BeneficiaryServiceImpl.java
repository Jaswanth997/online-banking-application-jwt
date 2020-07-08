package com.springboot.jpa.onlinebanking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.jpa.onlinebanking.dao.BeneficiaryRepository;
import com.springboot.jpa.onlinebanking.dao.CustomerDetailsRepository;
import com.springboot.jpa.onlinebanking.entity.Beneficiary;
import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.exceptions.BeneficiaryNotFoundException;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	
	private BeneficiaryRepository beneficiaryRepository;
	
	private CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	public BeneficiaryServiceImpl (BeneficiaryRepository BeneficiaryRepository,CustomerDetailsRepository CustomerDetailsRepository) {
		this.beneficiaryRepository =BeneficiaryRepository;
		this.customerDetailsRepository =CustomerDetailsRepository;
		
	}
	
	

	@Override
	public List<Beneficiary> findAll() {
		return beneficiaryRepository.findAll();
	}
	
	@Override
	public Page<Beneficiary> getAllBeneficiaries(int pageNumber, int itemsPerPage) {
		
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		return beneficiaryRepository.findAll(pageable);
	}

	@Override
	public Page<Beneficiary> getSortBeneficiaries(int pageNumber, int itemsPerPage, String fieldName) {
		
		Pageable pageable =PageRequest.of(pageNumber, itemsPerPage, Sort.by(fieldName));
		return beneficiaryRepository.findAll(pageable);
	}

	@Override
	public Beneficiary findById(int beneficiaryId) {
		
        Optional<Beneficiary> result = beneficiaryRepository.findById(beneficiaryId);
		
        Beneficiary beneficiary=null;
		if(result.isPresent()) {
			beneficiary=result.get();
		}else {
			throw new BeneficiaryNotFoundException("Beneficiary id not found : "+beneficiaryId);//custom exception
		}
		return beneficiary;
	}

	@Override
	public Beneficiary save(Beneficiary beneficiary) {
		 return beneficiaryRepository.save(beneficiary);
	}

	@Override
	public void deleteById(int beneficiaryId) {
		beneficiaryRepository.deleteById(beneficiaryId);
	}

	@Override
	public String addBeneficiary(int id, Beneficiary beneficiary) {

			String message = "";
			CustomerDetails customer = customerDetailsRepository.findById(id).get();

			if (customer == null) {
				message = "Customer not found";
				return message;
			} else {
				beneficiary.setCustomerDetails(customer);

				beneficiaryRepository.save(beneficiary);
				message = "Beneficiary added Successfully";
			}
			return message;
		}



	@Override
	public Beneficiary findByFirstName(String firstName) {
		return beneficiaryRepository.findbyFirstName(firstName);
	}



	@Override
	public Beneficiary findByLastName(String lastName) {
		return beneficiaryRepository.findbyLastName(lastName);
	}



	@Override
	public Beneficiary findByEmail(String emailId) {
		return beneficiaryRepository.findbyEmail(emailId);
	}



	@Override
	public Beneficiary findByAccountNumber(String accountNumber) {
		return beneficiaryRepository.findbyAccountNumber(accountNumber);
	}



	@Override
	public Beneficiary findByPhoneNumber(String phoneNumber) {
		return beneficiaryRepository.findbyPhoneNumber(phoneNumber);
	}



	@Override
	public List<Beneficiary> findAllBeneficiaries(Beneficiary beneficiary) {
		return beneficiaryRepository.findAll();
	}
	}


