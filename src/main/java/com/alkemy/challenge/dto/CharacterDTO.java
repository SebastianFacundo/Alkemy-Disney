package com.alkemy.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private long id;
    private String imagen;
    private String nombre;
    private int edad;
    private long peso;
    private String historia;
    private List<MovieDTO> peliculas;
}
