package com.springboot.jpa.onlinebanking;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.entity.TransactionDetails;
import com.springboot.jpa.onlinebanking.service.CustomerDetailsService;

@SpringBootTest
public class CustomerTest {

	@Autowired
	private CustomerDetailsService customerDetailsService;

	CustomerDetails customerDetails;
	
	@BeforeEach
	void addBeneficiary() {

		customerDetails = new CustomerDetails();

		customerDetails.setFirstName("Rama");
		customerDetails.setLastName("Devi");
		customerDetails.setAccountNumber("5786564345378965");
		customerDetails.setPhoneNumber("8790397296");
		customerDetails.setEmailId("Himaja@gmail.com");
		customerDetails.setAadharNumber("567845671234");
		customerDetails.setAddress("Andhra Pradesh");
		customerDetails.setGender("Female");
		customerDetails.setDateOfBirth("29-02-1970");
        customerDetails.setOpeningBalance("10000");
        customerDetails.setPin("2625");
		customerDetails.setRole("ROLE_USER");
        customerDetails.setPassword("Qwerty@123");
		customerDetails.setBankBranch("Tirupati");
		customerDetails.setBeneficiaryList(null);
		customerDetails.setTranscationDetailsList(null);
		customerDetails.setRequestList(null);


	}

	@Test
	void TestSearchPositiveTest() {
		CustomerDetails customer1 = customerDetailsService.findByEmail(customerDetails.getEmailId());
		assertNotNull(customer1);
	}
	
	@Test
	void TestSearchNegativeTest() {
		CustomerDetails customer1 = customerDetailsService.findByEmail(customerDetails.getEmailId());
		assertNotEquals(null,customer1);
	}

	@Test
	void getallPositiveTest() {
		List<CustomerDetails>customersList = customerDetailsService.findAllCustomers(customerDetails);
		assertNotNull(customersList);
	}
	
	@Test
	void getallNegativeTest() {
		List<CustomerDetails>customersList = customerDetailsService.findAllCustomers(customerDetails);
		assertNotEquals(null,customersList);
	}


	@Test
	void getMyTransactionPositiveTest() {
		int id = 1;
		List<TransactionDetails> tranList = customerDetailsService.myTransaction(id);
		assertNotNull(tranList);
	}
	
	@Test
	void getMyTransactionNegativeTest() {
		int id = 1;
		List<TransactionDetails> tranList = customerDetailsService.myTransaction(id);
		assertNotEquals(null,tranList);
	}
	
}