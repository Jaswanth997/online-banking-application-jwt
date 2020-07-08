package com.springboot.jpa.onlinebanking;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa.onlinebanking.entity.Request;
import com.springboot.jpa.onlinebanking.service.RequestService;

@SpringBootTest
public class RequestTest {
	
	@Autowired
	private RequestService requestService;
	
	@Test
	void getRequestPositivetest() {
		List<Request> request = requestService.findAll();
		assertNotNull(request);
	}
	
	@Test
	void getRequestNegativeTest() {
		List<Request> request = requestService.findAll();
		assertNotEquals(null,request);
	}

	@Test
	void getMyRequestsPositiveTest() {
		int id = 1;
		List<Request> request = requestService.myRequest(id);
		assertNotNull(request);
	}

	@Test
	void getMyRequestsNegativeTest() {
		int id = 1;
		List<Request> request = requestService.myRequest(id);
		assertNotEquals(null, request);
	}
}
