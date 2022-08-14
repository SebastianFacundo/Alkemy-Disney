package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.GenderDTO;
import com.alkemy.challenge.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("generos")
public class GenderController {

    @Autowired
    GenderService genderService;
    @PostMapping
    public ResponseEntity<GenderDTO> save (@RequestBody GenderDTO genero){
       GenderDTO generoGuardado= genderService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

}
