package com.delphi.mongo_rest_api;

import com.delphi.mongo_rest_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class MongoRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoRestApiApplication.class, args);
	}

}
