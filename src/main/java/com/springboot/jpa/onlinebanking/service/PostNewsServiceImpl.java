package com.springboot.jpa.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.jpa.onlinebanking.dao.PostNewsRepository;
import com.springboot.jpa.onlinebanking.entity.PostNews;

@Service
public class PostNewsServiceImpl implements PostNewsService {
	
	private PostNewsRepository postNewsRepository;
	
	@Autowired
	public PostNewsServiceImpl (PostNewsRepository PostNewsRepository) {
		this.postNewsRepository = PostNewsRepository;
		
	}

	@Override
	public List<PostNews> findAllNews() {
		return postNewsRepository.findAll();
	}

	@Override
	public PostNews save(PostNews news) {
		return postNewsRepository.save(news);
	}

	@Override
	public Page<PostNews> getAllNews(int pageNumber, int itemsPerPage) {
		
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		return postNewsRepository.findAll(pageable);
	
	}

	@Override
	public Page<PostNews> getSortedNews(int pageNumber, int itemsPerPage, String fieldName) {
		
		Pageable pageable =PageRequest.of(pageNumber, itemsPerPage, Sort.by(fieldName));
		return postNewsRepository.findAll(pageable);
	}

}
