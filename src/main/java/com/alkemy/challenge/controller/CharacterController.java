package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {


    private CharacterService characterService;


    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }


    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {

        List<CharacterDTO> response = characterService.getAllCharacters();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CharacterDTO> getDetailsCharacter(@PathVariable("id") Long id) {

        CharacterDTO response = characterService.getCharacter(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> addCharacter(@RequestBody CharacterDTO characterDTO) {

        CharacterDTO response = characterService.save(characterDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CharacterDTO> updateCharacter(@RequestBody CharacterDTO characterDTO) {

        CharacterDTO response = characterService.update(characterDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CharacterDTO> deleteCharacter(@PathVariable("id") Long id) {

        CharacterDTO response = characterService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<CharacterBasicDTO> getForName(@RequestParam("name") String name) {

        CharacterBasicDTO response= characterService.getCharacterForName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "edad")
    public ResponseEntity<List<CharacterBasicDTO>> getForAge(@RequestParam("edad") int edad) {

       List<CharacterBasicDTO> response= characterService.getCharactersForAge(edad);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "idMovie")
    public ResponseEntity<List<CharacterBasicDTO>> getForIdMovie(@RequestParam("idMovie") Long idMovie) {

        List<CharacterBasicDTO> response= characterService.getCharactersForMovie(idMovie);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
