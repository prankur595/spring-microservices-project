package com.prankur.microservices.product;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

@Slf4j
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests
{
//	@ServiceConnection
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private int port;

	@BeforeEach
	void setup()
	{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

//	static
//	{
//		mongoDBcontainer.start();
//	}

	@Test
	void shouldCreateProduct()
	{
		String requestBody = """
				{
				    "name": "iPhone 16",
				    "description": "the most expensive anymore",
				    "price": 190000
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201);
//				.body("name",  Matchers.notNullValue())
//				.body("description", (ResponseAwareMatcher<Response>) Matchers.notNullValue())
//				.body("price", (ResponseAwareMatcher<Response>) Matchers.notNullValue());
	}

}
