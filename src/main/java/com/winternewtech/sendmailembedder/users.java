package com.winternewtech.sendmailembedder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class users {

	@Id
	public String Id;

	public String name;
	public String email;
	public String project;

	public users(String name, String email, String project) {
		this.name = name;
		this.email = email;
		this.project = project;
	}

	public String print(users user) {
		return String.format("%s %s %s %s", user.Id, user.name, user.email, user.project);
	}

}