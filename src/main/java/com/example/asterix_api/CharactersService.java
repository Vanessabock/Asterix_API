package com.example.asterix_api;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharactersService {
    private final CharacterRepo characterRepo;
    private final IdService idService;

    public List<Characters> getAllCharacters(){
        return characterRepo.findAll();
    }

    public List<Characters> findCharactersByAgeAndName(int age, String name){
        return characterRepo.findCharactersByAgeAndName(age, name);
    }

    public List<Characters> findCharactersByAge(int age){
        return characterRepo.findCharactersByAge(age);
    }

    public List<Characters> findCharacterById(String id){
        return characterRepo.findCharactersById(id);
    }

    public Characters addNewCharacter(CharactersCreate charactersCreate){
        Characters character = new Characters(
                idService.randomId(),
                charactersCreate.name(),
                charactersCreate.age(),
                charactersCreate.profession(),
                System.currentTimeMillis() / 1000
        );
        return characterRepo.save(character);
    }

    public void updateAgeById(String id, int age){
        Optional<Characters> character = characterRepo.findById(id);
        if(character.isPresent()){
            characterRepo.delete(character.get());
            characterRepo.save(character.get().withAge(age));
        }
    }

    public void deleteCharacterByID(String id){
        Optional<Characters> character = characterRepo.findById(id);
        character.ifPresent(characterRepo::delete);
    }
}
