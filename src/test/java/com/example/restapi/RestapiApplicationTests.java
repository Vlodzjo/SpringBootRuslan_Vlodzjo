package com.example.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class RestapiApplicationTests {

	@Test
	void contextLoads() {
		UUID uuid = UUID.fromString("2a1e7834-5683-406e-831b-af37d94fbc6f");
		System.out.println();
	}

}
