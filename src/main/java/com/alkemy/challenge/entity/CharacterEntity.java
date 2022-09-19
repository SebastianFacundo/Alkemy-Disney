package com.alkemy.challenge.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "personaje")
@Getter
@Setter
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imagen;
    private String nombre;
    private int edad;
    private long peso;
    private String historia;
    private Boolean borrado = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personajes")
    private List<MovieEntity> peliculas = new ArrayList<>();


}
