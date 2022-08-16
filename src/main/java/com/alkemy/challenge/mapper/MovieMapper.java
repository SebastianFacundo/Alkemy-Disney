package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.dto.MovieDTO;
import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.MovieEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieMapper {
    public MovieEntity dtoToEntity(MovieDTO dto) {
        MovieEntity movieEntity = new MovieEntity();
        List<CharacterEntity> characterEntities = new ArrayList<>();

        movieEntity.setImagen(dto.getImagen());
        movieEntity.setTitulo(dto.getTitulo());
        movieEntity.setFechaCreacion(dto.getFechaCreacion());
        movieEntity.setCalificacion(dto.getCalificacion());
        movieEntity.setGenero(dto.getGenero());

        for (CharacterDTO character : dto.getPersonajes()) {
            CharacterEntity characterEntity = new CharacterEntity();

            characterEntity.setId(character.getId());
            characterEntity.setImagen(character.getImagen());
            characterEntity.setNombre(character.getNombre());
            characterEntity.setEdad(character.getEdad());
            characterEntity.setPeso(character.getPeso());
            characterEntity.setHistoria(character.getHistoria());
            characterEntities.add(characterEntity);
        }
        movieEntity.setPersonajes(characterEntities);

        return movieEntity;
    }

    public MovieDTO entityToDto(MovieEntity movieEntity) {
        MovieDTO movieDTO = new MovieDTO();
        List<CharacterDTO> characterDTOS = new ArrayList<>();

        movieDTO.setId(movieEntity.getId());
        movieDTO.setImagen(movieEntity.getImagen());
        movieDTO.setTitulo(movieEntity.getTitulo());
        movieDTO.setFechaCreacion(movieEntity.getFechaCreacion());
        movieDTO.setCalificacion(movieEntity.getCalificacion());
        movieDTO.setGenero(movieEntity.getGenero());

        for (CharacterEntity character : movieEntity.getPersonajes()) {
            CharacterDTO characterDTO = new CharacterDTO();

            characterDTO.setId(character.getId());
            characterDTO.setImagen(character.getImagen());
            characterDTO.setNombre(character.getNombre());
            characterDTO.setEdad(character.getEdad());
            characterDTO.setPeso(character.getPeso());
            characterDTO.setHistoria(character.getHistoria());
            characterDTOS.add(characterDTO);
        }

        movieDTO.setPersonajes(characterDTOS);

        return movieDTO;
    }


    public MovieEntity refresh(MovieDTO movieDTO) {
        MovieEntity updatedMovie = new MovieEntity();

        updatedMovie.setId(movieDTO.getId());
        updatedMovie.setImagen(movieDTO.getImagen());
        updatedMovie.setTitulo(movieDTO.getTitulo());
        updatedMovie.setFechaCreacion(movieDTO.getFechaCreacion());
        updatedMovie.setCalificacion(movieDTO.getCalificacion());
        updatedMovie.setGenero(movieDTO.getGenero());

        return updatedMovie;

    }


    public MovieBasicDTO entityToBasicDto(MovieEntity movieEntity) {

        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setTitulo(movieEntity.getTitulo());
        movieBasicDTO.setFechaCreacion(movieEntity.getFechaCreacion());

        return movieBasicDTO;
    }
}
