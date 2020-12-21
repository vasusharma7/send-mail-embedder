package com.winternewtech.sendmailembedder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class users {

	@Id
	private String name;
    private String email;
    private String project;

	public users(String name, String email, String project) {
		this.name = name;
		this.email = email;
		this.project = project;
	}
}