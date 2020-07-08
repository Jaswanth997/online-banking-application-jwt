package com.springboot.jpa.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.jpa.onlinebanking.dao.TransactionDetailsRepository;
import com.springboot.jpa.onlinebanking.entity.TransactionDetails;

@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactionDetailsRepository transactionDetailsRepository;

	@Autowired
	public TransactionServiceImpl(TransactionDetailsRepository theTransactiondetailsRepository) {
		this.transactionDetailsRepository = theTransactiondetailsRepository;
	}

	@Override
	public List<TransactionDetails> findAllTransaction() {

		return transactionDetailsRepository.findAll();
	}

	@Override
	public Page<TransactionDetails> getAllRequests(int pageNumber, int itemsPerPage) {
		 Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
			return transactionDetailsRepository.findAll(pageable);
	}

	@Override
	public Page<TransactionDetails> getSortRequests(int pageNumber, int itemsPerPage, String fieldName) {
		Pageable pageable =PageRequest.of(pageNumber, itemsPerPage, Sort.by(fieldName));
		return transactionDetailsRepository.findAll(pageable);
	}

}