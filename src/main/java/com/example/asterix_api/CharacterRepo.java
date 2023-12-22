package com.example.asterix_api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepo extends MongoRepository<Characters, String> {
    @Query("{$or: [{'age': ?0}, {'age': null}]}")
    List<Characters> findCharactersByAgeOrAll(Integer age);
    List<Characters> findCharactersByAgeAndName(Integer age, String name);
    List<Characters> findCharactersByAge(Integer age);
    List<Characters> findCharactersById(String id);
}
