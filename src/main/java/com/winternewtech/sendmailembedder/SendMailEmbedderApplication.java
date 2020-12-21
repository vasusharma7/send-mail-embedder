package com.winternewtech.sendmailembedder;

import com.winternewtech.sendmailembedder.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.winternewtech.sendmailembedder.users;
import com.winternewtech.sendmailembedder.userrepository;

@SpringBootApplication
@RestController
public class SendMailEmbedderApplication implements CommandLineRunner {

	@Autowired
	userrepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SendMailEmbedderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public String register(@RequestBody RegisterData data) {
		System.out.println(String.format("%s %s %s", data.name, data.email, data.project));
		repository.save(new users(data.name, data.email, data.project));
		repository.findAll().forEach(u -> {
			System.out.println(String.format("%s %s %s %s", u.name, u.Id, u.project, u.email));
		});
		return "hey";
	}

}
