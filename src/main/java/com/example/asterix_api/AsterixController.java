package com.example.asterix_api;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AsterixController {

    private final CharacterRepo characterRepo;

    public AsterixController(CharacterRepo characterRepo){
        this.characterRepo = characterRepo;
    }

    @GetMapping("/asterix/characters")
    public List<Characters> getAllCharacters(@RequestParam Optional<Integer> age,
                                             @RequestParam Optional<String> name){
        if (age.isPresent() && name.isPresent()){
            return characterRepo.findCharactersByAgeAndName(age.get(), name.get());
        }
        if(age.isPresent()){
            return characterRepo.findCharactersByAge(age.get());
        }
        return characterRepo.findAll();
    }

    @PostMapping("/asterix/characters")
    public void addNewCharacter(@RequestBody Characters character){
        characterRepo.save(character);
    }

    @PutMapping("/asterix/characters/{id}")
    public void updateAgeById(@PathVariable String id, @RequestParam int age){
        Optional<Characters> character = characterRepo.findById(id);
        if(character.isPresent()){
            characterRepo.delete(character.get());
            characterRepo.save(character.get().withAge(age));
        }
    }

    @DeleteMapping("/asterix/characters/{id}")
    public void deleteCharacterById(@PathVariable String id){
        Optional<Characters> character = characterRepo.findById(id);
        character.ifPresent(characterRepo::delete);
    }
}
