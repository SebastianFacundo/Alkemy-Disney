package com.alkemy.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieBasicDTO {
    private String titulo;
    private LocalDate fechaCreacion;
}
