package com.alkemy.challenge.dto;

import com.alkemy.challenge.entity.GenderEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieDTOForFindAll {

    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private GenderEntity genero;
}
