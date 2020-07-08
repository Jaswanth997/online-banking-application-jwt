package com.springboot.jpa.onlinebanking.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.jpa.onlinebanking.entity.TransactionDetails;

public interface TransactionService {
	
	public List<TransactionDetails> findAllTransaction();
	
	public Page<TransactionDetails> getAllRequests(int pageNumber, int itemsPerPage);
    
    public Page<TransactionDetails> getSortRequests(int pageNumber, int itemsPerPage, String fieldName);
	
	

}
