package com.winternewtech.sendmailembedder;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.winternewtech.sendmailembedder.users;

public interface userrepository extends MongoRepository<users, String> {
}