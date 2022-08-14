package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {


    private CharacterService characterService;


    private CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    /*
    Mensaje para mentor: Tengo un bucle infinito al traer un personaje porque una pelicula tambien tiene
     personajes. Para poder mostrar el detalle use un dto de peliculas sin personajes pero no es la solucion
     ya que me fije con el debug e internamente sigo teniendo un bucle
     */

    @GetMapping
    public ResponseEntity <List<CharacterDTO>> getAllCharacters(){

        List<CharacterDTO> characters = characterService.getAllCharacters();


        return new ResponseEntity<>(characters,HttpStatus.OK);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<CharacterDTO>getDetailsCharacter(@PathVariable("id") Long id) {

        return new ResponseEntity<>(characterService.getCharacter(id), HttpStatus.OK);
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

    @DeleteMapping(path="/{id}")
    public ResponseEntity<CharacterDTO> deleteCharacter(@PathVariable("id") Long id) {

        CharacterDTO response=characterService.delete(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
