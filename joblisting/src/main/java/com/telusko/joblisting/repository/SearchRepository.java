package com.telusko.joblisting.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.telusko.joblisting.model.Post;

//we cant use some inbuilt function of repository
//for filtering in /post/{text},
//therefore we will be custom  implementing these:

@Repository
public interface SearchRepository {
	
	List<Post> findByText(String text);

}
