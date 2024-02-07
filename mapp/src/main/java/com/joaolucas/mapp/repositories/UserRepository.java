package com.joaolucas.mapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.model.Usuario;

@Repository
public interface UserRepository extends MongoRepository<Usuario, String> {

	UserDetails findByEmail(String email);

}
