package com.getircase.readingisgood;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@SpringBootTest
class ReadingisgoodApplicationTests {

	@Test
	void contextLoads() {
	}

}
