package com.springboot.jpa.onlinebanking.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.jpa.onlinebanking.entity.Request;

public interface RequestService {
	
	    public List<Request> findAll();
	    
	    public Page<Request> getAllRequests(int pageNumber, int itemsPerPage);
	    
	    public Page<Request> getSortRequests(int pageNumber, int itemsPerPage, String fieldName);
	    
	    public Request findById(int requestId);
	    
	    //public Request save(Request request);
	    
	    public Request setSuccess(int id);
	    
	    public Request setChanged(int id);
	    
	    public Request setInProgress(int id);
	    
	    public String addRequest(int id,Request request);
	    
	    public List<Request> myRequest(int id);
}
