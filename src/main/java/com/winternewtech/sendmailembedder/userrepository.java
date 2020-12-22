package com.winternewtech.sendmailembedder;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.winternewtech.sendmailembedder.users;

public interface userrepository extends MongoRepository<users, String> {

    @Query("{ 'project': ?0, 'email': ?1}")
    public List<users> findUser(String project, String email);

    @Query("{ 'Id' : ?0 }")
    Optional<users> findWithId(String Id);
}