	package com.telusko.joblisting.repository;
	
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;
	
	import org.bson.Document;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.mongodb.core.convert.MongoConverter;
	import org.springframework.stereotype.Component;
	
	import com.mongodb.client.AggregateIterable;
	import com.mongodb.client.MongoChangeStreamCursor;
	import com.mongodb.client.MongoClient;
	import com.mongodb.client.MongoCollection;
	import com.mongodb.client.MongoDatabase;
	import com.telusko.joblisting.model.Post;
	
	@Component
	public class SearchRepositoryImpl implements SearchRepository {
		@Autowired
		MongoClient mongoClient;
		@Autowired
		MongoConverter converter;
	
		@Override
		public List<Post> findByText(String text) {
			List<Post> srchposts=new ArrayList<>();
			MongoDatabase database = mongoClient.getDatabase("telusko");
			MongoCollection<Document> collection = database.getCollection("JobPost");
			AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("text", 
			    new Document("query", text)
			                .append("path", Arrays.asList("techs", "desc", "profile")))), 
			    new Document("$sort", 
			    new Document("exp", 1L)), 
			    new Document("$limit", 5L)));
	
	
			result.forEach(doc -> srchposts.add(converter.read(Post.class, doc)));
	
			return srchposts;
		}
	
	}
