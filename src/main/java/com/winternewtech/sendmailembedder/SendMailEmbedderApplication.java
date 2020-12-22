package com.winternewtech.sendmailembedder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.winternewtech.sendmailembedder.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrProperties;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.winternewtech.sendmailembedder.users;
import com.winternewtech.sendmailembedder.userrepository;

@SpringBootApplication
@RestController
public class SendMailEmbedderApplication implements CommandLineRunner {

	@Autowired
	public userrepository repository;

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
		// System.out.println(String.format("%s %s %s %s", u.name, u._i`d, u.project,
		// u.email));
		// });
		List<users> record = repository.findUser(data.project, data.email);

		return new Response(200, record.get(0)._id);
	}

	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public Response login(@RequestBody LoginData data, HttpServletResponse response) {
		// System.out.println(String.format("%s %s %s", data.name, data.email,
		// data.project));
		var record = repository.findById(data.id);
		if (record.isPresent()) {
			users entity=record.get();
			if (entity.email==data.email && entity.name==data.name){
				System.out.println("ound");
				return new Response(200, "Login Successfull");
			}
		}
		System.out.println("not found");
		return new Response(400, "Wrong Credentials");
	}

	@PostMapping(value = "/send/{key}")
	public Response sendEmail(@PathVariable String key, @RequestBody Map<String, Object> data) {
		System.out.println(key);
		try {
			key=key.replace("\n","");
			var record = repository.findById(key);

			if (record.isPresent()) {
				users entity = record.get();
				System.out.println(entity);
				Email.sendmail(entity, data);
			} else {
				System.out.println("not found");

			}
		} catch (Exception e) {
			System.out.println(e);
			return new Response(400, e.getMessage());
		}
		return new Response(200, "success");

	}

}

// if (u.isPresent()) {
// System.out.println(u.get());
// } else {
// System.out.println("Employee not found!");
// }