package com.example.asterix_api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AsterixController {

    private final CharactersService charactersService;

    @GetMapping("/asterix/characters")
    public List<Characters> getAllAll(@RequestParam Optional<String> id,
                                      @RequestParam Optional<Integer> age,
                                      @RequestParam Optional<String> name){
        if (age.isPresent() && name.isPresent()){
            return charactersService.findCharactersByAgeAndName(age.get(), name.get());
        }
        if(age.isPresent()){
            return charactersService.findCharactersByAge(age.get());
        }
        if (id.isPresent()){
            return charactersService.findCharacterById(id.get());
        }
        return charactersService.getAllCharacters();
    }

    @PostMapping("/asterix/characters")
    public Characters addNewCharacter(@RequestBody CharactersCreate charactersCreate){
        return charactersService.addNewCharacter(charactersCreate);
    }

    @PutMapping("/asterix/characters/{id}")
    public void updateAgeById(@PathVariable String id, @RequestParam int age){
        charactersService.updateAgeById(id, age);
    }

    @DeleteMapping("/asterix/characters/{id}")
    public void deleteCharacterById(@PathVariable String id){
        charactersService.deleteCharacterByID(id);
    }
}
