package com.springboot.jpa.onlinebanking.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.jpa.onlinebanking.entity.PostNews;

public interface PostNewsService {
	
     public List<PostNews> findAllNews();
     
     public PostNews save(PostNews news );
     
     public Page<PostNews> getAllNews(int pageNumber, int itemsPerPage);
	    
 	public Page<PostNews> getSortedNews(int pageNumber, int itemsPerPage, String fieldName);
	
	
	

}
