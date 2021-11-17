package com.example.demo;

import com.example.demo.helpers.HoverflyHelper;
import io.specto.hoverfly.junit.core.Hoverfly;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private Hoverfly hoverfly;

	@Test
	void contextLoads() {
	}

	@Test
	void testHoverfly() {
		try (HoverflyHelper ignored = HoverflyHelper.simulateOrCapture(hoverfly, "test")) {
			HttpClient client = HttpClient.create();
			var response = client.baseUrl("127.0.0.1:8080/ping").get();
			System.out.println(response.response().block());
		} catch (Exception e) {
			System.out.println("NOT OK");
		}
	}
}
