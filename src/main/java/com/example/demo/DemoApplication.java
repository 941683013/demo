package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @SpringBootApplication 来标注一个主程序
 */
@SpringBootApplication
@RestController
public class DemoApplication {

	public static HashMap<String, String> hashMap = new HashMap<>();

	public static Queue<Pair> queue = new LinkedList<>();
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				long current = System.currentTimeMillis();
				while(!queue.isEmpty()) {
					Pair pair = queue.peek();
					if((current - pair.getFirst()) / 1000 / 60 >= 5) {
						queue.poll();
						hashMap.remove(pair.getSecond());
					}
					else {
						break;
					}
				}

			}
		}, 60 * 1000);
	}

}
