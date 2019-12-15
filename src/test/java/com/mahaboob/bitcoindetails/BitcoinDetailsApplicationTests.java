package com.mahaboob.bitcoindetails;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;

@SpringBootTest
class BitcoinDetailsApplicationTests {
	
	@BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

	@Test
	public void testCurrency() {
		when().get("bitcoin/getBitcoinDetails?currency=INR")
		.then().statusCode(200)
		.body("Currency", equalTo("INR"));
	}
	
	@Test
	public void testInvalidCurrency() {
		when().get("bitcoin/getBitcoinDetails?currency=ABC")
		.then().statusCode(200)
		.body(equalTo("Sorry, your requested currency ABC is not supported or is invalid"));
	}

}
