package com.mahaboob.bitcoindetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
public class BitcoinDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinDetailsApplication.class, args);
	}

}
