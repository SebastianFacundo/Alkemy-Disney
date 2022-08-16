package com.alkemy.challenge.dto;

import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.GenderEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {

    private long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private List<CharacterDTO> personajes;
    private GenderEntity genero;
}
