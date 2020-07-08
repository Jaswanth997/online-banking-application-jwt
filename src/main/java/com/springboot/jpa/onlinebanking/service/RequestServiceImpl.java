package com.springboot.jpa.onlinebanking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.jpa.onlinebanking.dao.CustomerDetailsRepository;
import com.springboot.jpa.onlinebanking.dao.RequestRepository;
import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.entity.Request;

@Service
public class RequestServiceImpl implements RequestService {
	
	private RequestRepository requestRepository;
	
	private CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	public RequestServiceImpl (RequestRepository RequestRepository, CustomerDetailsRepository CustomerDetailsRepository) {
		this.requestRepository = RequestRepository;
		this.customerDetailsRepository= CustomerDetailsRepository;
		
	}

	@Override
	public List<Request> findAll() {
		return requestRepository.findAll();
	}

	@Override
	public Page<Request> getAllRequests(int pageNumber, int itemsPerPage) {
		
        Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		return requestRepository.findAll(pageable);
		
	}

	@Override
	public Page<Request> getSortRequests(int pageNumber, int itemsPerPage, String fieldName) {
		
		Pageable pageable =PageRequest.of(pageNumber, itemsPerPage, Sort.by(fieldName));
		return requestRepository.findAll(pageable);
	}
	
	@Override
	public Request findById(int requestId) {
     Optional<Request> result = requestRepository.findById(requestId);
		
		Request request=null;
		if(result.isPresent()) {
			request=result.get();
		}
		return request;
	}
	
	
	@Override
	public String addRequest(int id, Request request) {
		
		String message ="";
		
		CustomerDetails customer = customerDetailsRepository.findById(id).get();
		
		if(customer != null) {
			request.setDetails("This request was sent by account number:"+ customer.getAccountNumber());
			request.setCustomerDetails(customer);
			message ="Request sent Successfully";
			
		requestRepository.save(request);
		}
		return message;
	}

	@Override
	public List<Request> myRequest(int id) {
		
		CustomerDetails customer = customerDetailsRepository.findById(id).get();
		if(customer != null) {
			return customer.getRequestList();
		}
		return null;
	}

	

	@Override
	public Request setSuccess(int id) {
		Optional<Request> result = requestRepository.findById(id);
		Request customer = null;
		customer = result.get();
		customer.setStatus("Success");
		requestRepository.save(customer);
		return customer;
	}

	@Override
	public Request setChanged(int id) {
		Optional<Request> result = requestRepository.findById(id);
		Request customer = null;
		customer = result.get();
		customer.setStatus("Changed");
		requestRepository.save(customer);
		return customer;
	}

	@Override
	public Request setInProgress(int id) {
		Optional<Request> result = requestRepository.findById(id);
		Request customer = null;
		customer = result.get();
		customer.setStatus("In-Progress");
		requestRepository.save(customer);
		return customer;
	}
}
