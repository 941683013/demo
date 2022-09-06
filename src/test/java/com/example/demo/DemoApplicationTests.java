package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	MyMailService mail;

	@Test
	void contextLoads() {
		mail.sendMail("YDS", "3572424627@qq.com",  "Title", "hello !");
	}

}
