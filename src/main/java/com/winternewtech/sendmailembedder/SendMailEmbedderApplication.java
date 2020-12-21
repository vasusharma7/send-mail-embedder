package com.winternewtech.sendmailembedder;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.winternewtech.sendmailembedder.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrProperties;
import org.springframework.data.convert.ClassGeneratingEntityInstantiator;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Response register(@RequestBody RegisterData data, HttpServletResponse response) {
		// System.out.println(String.format("%s %s %s", data.name, data.email,
		// data.project));
		List<users> dump = repository.findUser(data.project, data.email);
		if (dump.size() != 0) {
			return new Response(400, "User already exists");
		}

		repository.save(new users(data.name, data.email, data.project));
		// repository.findAll().forEach(u -> {
		// System.out.println(String.format("%s %s %s %s", u.name, u.Id, u.project,
		// u.email));
		// });
		List<users> record = repository.findUser(data.project, data.email);

		return new Response(200, record.get(0).Id);
	}

	@PostMapping(value = "/send/{key}", consumes = "application/json", produces = "application/json")
	public Response sendEmail(@PathVariable String key, @RequestBody Map<String, Object> data) {
		System.out.println(key);
		try {
			Email.sendmail(new users("Vasu", "vasusharma2017@outlook.com", "test"), data);
		} catch (Exception e) {
			System.out.println(e);
			return new Response(400, e.getMessage());
		}
		return new Response(200, "success");

	}

}
