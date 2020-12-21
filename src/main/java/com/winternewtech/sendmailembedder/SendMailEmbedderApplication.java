package com.winternewtech.sendmailembedder;

import com.winternewtech.sendmailembedder.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
@RestController
public class SendMailEmbedderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendMailEmbedderApplication.class, args);
	}

	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public String register(@RequestBody RegisterData data) {
		System.out.println(String.format("%s %s %s", data.name, data.email, data.project));
		return "hey";
	}
}
