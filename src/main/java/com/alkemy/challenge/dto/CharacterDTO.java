package com.alkemy.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private long id;
    @NotBlank(message = "La imagen no puede estar vacio")
    private String imagen;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "La edad no puede estar vacio")
    private int edad;
    @NotEmpty(message = "El peso no puede estar vacio")
    private long peso;
    @NotBlank(message = "La historia no puede estar vacio")
    private String historia;
    @NotEmpty(message = "Peliculas no puede estar vacio")
    private List<MovieDTO> peliculas;
}
