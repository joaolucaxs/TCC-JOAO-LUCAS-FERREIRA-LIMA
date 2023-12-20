package com.joaolucas.mapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaolucas.mapp.model.Html;

@Repository
public interface HtmlRepository extends MongoRepository<Html, String> {
}
