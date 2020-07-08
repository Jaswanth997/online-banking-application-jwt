package com.springboot.jpa.onlinebanking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.jpa.onlinebanking.dao.CustomerDetailsRepository;
import com.springboot.jpa.onlinebanking.entity.AtmSimulator;
import com.springboot.jpa.onlinebanking.entity.Beneficiary;
import com.springboot.jpa.onlinebanking.entity.Credit;
import com.springboot.jpa.onlinebanking.entity.CustomerDetails;
import com.springboot.jpa.onlinebanking.entity.TransactionDetails;
import com.springboot.jpa.onlinebanking.entity.TransferFunds;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	private CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	public CustomerDetailsServiceImpl(CustomerDetailsRepository CustomerDetailsRepository) {
		this.customerDetailsRepository = CustomerDetailsRepository;

	}
   
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<CustomerDetails> findAll() {
		return customerDetailsRepository.findAll();
	}

	@Override
	public Page<CustomerDetails> getAllCustomers(int pageNumber, int itemsPerPage) {

		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		return customerDetailsRepository.findAll(pageable);
	}

	@Override
	public Page<CustomerDetails> getSortedCustomers(int pageNumber, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage, Sort.by(fieldName));
		return customerDetailsRepository.findAll(pageable);
	}

	@Override
	public CustomerDetails findById(int customerId) {
		Optional<CustomerDetails> result = customerDetailsRepository.findById(customerId);

		CustomerDetails customerDetails = null;
		if (result.isPresent()) {
			customerDetails = result.get();
		} 
		return customerDetails;
	}

	@Override
	public CustomerDetails save(CustomerDetails customerDetails) {
		customerDetails.setPassword(passwordEncoder.encode(customerDetails.getPassword()));
		return customerDetailsRepository.save(customerDetails);

	}//password encoder
	
	@Override
	public CustomerDetails update(CustomerDetails customerDetails) {
	
		return customerDetailsRepository.save(customerDetails);
	}

	@Override
	public void deleteById(int customerId) {
		customerDetailsRepository.deleteById(customerId);
	}

	@Override
	public CustomerDetails findByEmail(String emailId) {
    return customerDetailsRepository.findbyEmail(emailId);
		

	}

	@Override
	public CustomerDetails findByAadhar(String aadharNumber) {
		return customerDetailsRepository.findbyAadhar(aadharNumber);
	}

	@Override
	public CustomerDetails findByPhone(String phoneNumber) {
		return customerDetailsRepository.findbyPhone(phoneNumber);
	}

	@Override
	public CustomerDetails findByAccno(String accountNumber) {
		return customerDetailsRepository.findbyAccNo(accountNumber);
	}

	@Override
	public String atmSimulator(int id, AtmSimulator atm) {
		String message = "";

		CustomerDetails customer = customerDetailsRepository.findById(id).get();

		if (customer != null) {

			double balance = Double.parseDouble(customer.getOpeningBalance());
			double withdraw = Double.parseDouble(atm.getAmount());

			if (balance > withdraw) {

				if (atm.getPin().equals(customer.getPin())) {

					List<TransactionDetails> transaction = new ArrayList<TransactionDetails>();
					balance = balance - withdraw;

					customer.setOpeningBalance(balance + "");

					TransactionDetails transferDetails = new TransactionDetails();
					transferDetails.setDebit(withdraw + "");
					transferDetails.setCredit(0.00 + "");
					transferDetails.setBalance(balance + "");
					transferDetails.setDate(new Date());
					transferDetails.setRemarks("Withdrawn By ATM");
					transferDetails.setId("Transaction Done by Customer Id: " + customer.getCustomerId());
					transferDetails.setCustomerDetails(customer);

					transaction.add(transferDetails);

					customer.setTranscationDetailsList(transaction);

					customerDetailsRepository.save(customer);

					message = "Transaction Successful!";
				} else {
					message = "you entered wrong pin";
				}
			} else {
				message = "Don't have sufficient balance";
			}
		} else {
			message = "Id not found";
		}

		return message;

	}

	@Override
	public String transferFunds(int id, TransferFunds transferFunds) {
		String message = "";

		CustomerDetails customer = customerDetailsRepository.findById(id).get();

		if (customer != null) {

			boolean result = false;

			for (Beneficiary beneficiary : customer.getBeneficiaryList()) {

				if (transferFunds.getAccountNumber().equals(beneficiary.getAccountNumber())) {
					result = true;
				} else {
					message = "Invalid Account number";
				}
			}

			if (result) {
				double balance = Double.parseDouble(customer.getOpeningBalance());
				double transAmount = Double.parseDouble(transferFunds.getAmount());

				if (balance > transAmount) {

					if (transferFunds.getPin().equals(customer.getPin())) {

						List<TransactionDetails> transaction = new ArrayList<TransactionDetails>();
						balance = balance - transAmount;

						customer.setOpeningBalance(balance + "");

						TransactionDetails transferDetails = new TransactionDetails();
						transferDetails.setDebit(transAmount + "");
						transferDetails.setCredit(0.00 + "");
						transferDetails.setBalance(balance + "");
						transferDetails.setDate(new Date());
						transferDetails.setId("Transaction Done by Customer Id: " + customer.getCustomerId());
						transferDetails
								.setRemarks("Transfer to Beneficiary, ACC.NO:" + transferFunds.getAccountNumber());
						transferDetails.setCustomerDetails(customer);

						transaction.add(transferDetails);

						customer.setTranscationDetailsList(transaction);

						customerDetailsRepository.save(customer);
						
						message = "Transaction Successful!";

					} else {
						message = "you entered incorrect pin";
					}

				} else {
					message = "Dont have sufficient balance";
				}
			}
		}

		return message;

	}

	@Override
	public List<TransactionDetails> myTransaction(int transactionId) {
		CustomerDetails customer = customerDetailsRepository.findById(transactionId).get();

		if (customer != null) {
			return customer.getTranscationDetailsList();
		}
		return null;

	}

	@Override
	public List<Beneficiary> myBeneficiary(int beneficiaryId) {

		CustomerDetails customer = customerDetailsRepository.findById(beneficiaryId).get();

		if (customer != null) {
			return customer.getBeneficiaryList();
		}
		return null;
	}

	@Override
	public String creditAmount(int id, Credit credit) {

			String message = "";

			CustomerDetails customer = customerDetailsRepository.findById(id).get();

			if (customer != null) {
				double amount1 = Double.parseDouble(customer.getOpeningBalance());

				double camt = Double.parseDouble(credit.getAmount());

				double add = amount1 + camt;

				customer.setOpeningBalance(add + "");

				customerDetailsRepository.save(customer);

				message = "Money Credited Successfully!";
			}

			return message;
		}

	@Override
	public List<CustomerDetails> findAllCustomers(CustomerDetails customerDetails) {
		return customerDetailsRepository.findAll();
	}
}
