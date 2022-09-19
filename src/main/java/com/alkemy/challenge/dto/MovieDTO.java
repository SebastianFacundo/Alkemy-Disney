package com.alkemy.challenge.dto;

import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.GenderEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {

    private long id;
    @NotBlank(message = "La imagen no puede estar vacio")
    private String imagen;
    @NotBlank(message = "El titulo no puede estar vacio")
    private String titulo;
    @NotEmpty(message = "La fecha no puede estar vacia")
    private LocalDate fechaCreacion;
    @Range(min = 0, max = 5, message = "El rango de calificacion deber ser entre 0 y 5")
    private int calificacion;
    @NotEmpty(message = "Personajes no puede estar vacio")
    private List<CharacterDTO> personajes;
    @NotEmpty(message = "Genero no puede estar vacio")
    private GenderEntity genero;
}
