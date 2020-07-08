package com.springboot.jpa.onlinebanking;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa.onlinebanking.entity.Beneficiary;
import com.springboot.jpa.onlinebanking.service.BeneficiaryService;
import com.springboot.jpa.onlinebanking.service.CustomerDetailsService;

@SpringBootTest
 class BeneficiaryTest {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@Autowired CustomerDetailsService customerDetailsService;
	Beneficiary beneficiary;
	
	Beneficiary beneficiary1 =null;
	
	@BeforeEach
	void addBeneficiary() {
		
		beneficiary = new Beneficiary();
		
		beneficiary.setFirstName("Ziva");
		beneficiary.setLastName("Dhoni");
		beneficiary.setAccountNumber("5699664345367890");
		beneficiary.setPhoneNumber("9898798943");
		beneficiary.setEmailId("abc@gmail.com");
		
		beneficiary = beneficiaryService.save(beneficiary);
		
	}
	
	@Test
	void addTest() {
		assertNotNull(beneficiary);
	}
		
	@Test
	void findAllTestPositiveTest() {
		List<Beneficiary> beneficiaries = beneficiaryService.findAllBeneficiaries(beneficiary);
		assertNotNull(beneficiaries);
	}
	
	@Test
	void findAllTestNegativeTest() {
		List<Beneficiary> beneficiaries = beneficiaryService.findAllBeneficiaries(beneficiary);
		assertNotEquals(null,beneficiaries);
	}
	
	@AfterEach() 
		void deleteTest() {
			beneficiary = beneficiaryService.findById(this.beneficiary.getBeneficiaryId());
			beneficiaryService.deleteById(beneficiary.getBeneficiaryId());
   }
	
	@Test
	void testAfterDelete() {
		assertNotNull(beneficiary);
		
	}
	
	@Test
	void myBeneficiaryPositiveTest() {
		int id =1;
		List<Beneficiary> benList = customerDetailsService.myBeneficiary(id);
		assertNotNull(benList);
	}
	
	@Test
	void myBeneficiaryNegativeTest() {
		int id =1;
		List<Beneficiary> benList = customerDetailsService.myBeneficiary(id);
		assertNotEquals(null,benList);
	}
	
}