package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.helpers.HoverflyHelper;
import io.specto.hoverfly.junit.core.Hoverfly;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class DemoApplicationTests {

	@Autowired
	private Hoverfly hoverfly;

	@Test
	void contextLoads() {
	}

	@Test
	void testHoverfly() {
		try (HoverflyHelper ignored = HoverflyHelper.simulateOrCapture(hoverfly, "test")) {
			OkHttpClient client = new OkHttpClient.Builder().build();
			var request = new Request.Builder().url("http://127.0.0.1:8080/helloworld").build();
			var response = client.newCall(request).execute();
			assertEquals("Healthy Connection", response.body().string());
		} catch (Exception e) {
			System.out.println("FAILURE" + e);
		}
	}
}
