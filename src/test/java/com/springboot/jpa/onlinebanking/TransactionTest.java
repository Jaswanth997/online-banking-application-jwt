package com.springboot.jpa.onlinebanking;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa.onlinebanking.entity.TransactionDetails;
import com.springboot.jpa.onlinebanking.service.CustomerDetailsService;
import com.springboot.jpa.onlinebanking.service.TransactionService;


@SpringBootTest
class TransactionTest {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Test
	void getAllTransactionsPositiveTest() {
		List<TransactionDetails> transList = transactionService.findAllTransaction();
		assertNotNull(transList);
	}

	@Test
	void getAllTransactionsNegativeTest() {
		List<TransactionDetails> transList = transactionService.findAllTransaction();
		assertNotEquals(null, transList);
	}

	@Test
	void getMyTransactionsPositiveTest() {
		int id = 1;
		List<TransactionDetails> transList = customerDetailsService.myTransaction(id);
		assertNotNull(transList);
	}

	@Test
	void getMyTransactionsNegativeTest() {
		int id = 1;
		List<TransactionDetails> transList = customerDetailsService.myTransaction(id);
		assertNotEquals(null, transList);
	}

}
