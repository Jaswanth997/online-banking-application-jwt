package com.springboot.jpa.onlinebanking;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa.onlinebanking.entity.PostNews;
import com.springboot.jpa.onlinebanking.service.PostNewsService;


@SpringBootTest
class PostNewsTest {

	@Autowired
	private PostNewsService postNewsService;

	@Test
	void getNewsPositiveTest() {
		List<PostNews> news = postNewsService.findAllNews();
		assertNotNull(news);
	}

	@Test
	void getNewsNegativeTest() {
		List<PostNews> news = postNewsService.findAllNews();
		assertNotEquals(null, news);
	}

}
