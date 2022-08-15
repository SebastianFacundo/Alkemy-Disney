package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.CharacterDTO;

import com.alkemy.challenge.dto.MovieDTO;
import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.MovieEntity;

import java.util.HashSet;
import java.util.Set;

public class CharacterMapper {

    public CharacterEntity dtoToEntity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setImagen(dto.getImagen());
        characterEntity.setNombre(dto.getNombre());
        characterEntity.setEdad(dto.getEdad());
        characterEntity.setPeso(dto.getPeso());
        characterEntity.setHistoria(dto.getHistoria());

        return characterEntity;
    }

    public CharacterDTO entityToDto(CharacterEntity characterEntity) {
        CharacterDTO characterDTO = new CharacterDTO();
        Set<MovieDTO> movieDTOS = new HashSet<>();

        characterDTO.setId(characterEntity.getId());
        characterDTO.setImagen(characterEntity.getImagen());
        characterDTO.setNombre(characterEntity.getNombre());
        characterDTO.setEdad(characterEntity.getEdad());
        characterDTO.setPeso(characterEntity.getPeso());
        characterDTO.setHistoria(characterEntity.getHistoria());

        for (MovieEntity movie : characterEntity.getPeliculas()) {
            MovieDTO movieDTO = new MovieDTO();

            movieDTO.setId(movie.getId());
            movieDTO.setImagen(movie.getImagen());
            movieDTO.setTitulo(movie.getTitulo());
            movieDTO.setFechaCreacion(movie.getFechaCreacion());
            movieDTO.setCalificacion(movie.getCalificacion());
            movieDTO.setGenero(movie.getGenero());
            movieDTOS.add(movieDTO);
        }

        characterDTO.setPeliculas(movieDTOS);

        return characterDTO;
    }


    public CharacterEntity refresh(CharacterDTO characterDTO){
        CharacterEntity updatedCharacter = new CharacterEntity();

        updatedCharacter.setId(characterDTO.getId());
        updatedCharacter.setImagen(characterDTO.getImagen());
        updatedCharacter.setNombre(characterDTO.getNombre());
        updatedCharacter.setEdad(characterDTO.getEdad());
        updatedCharacter.setPeso(characterDTO.getPeso());
        updatedCharacter.setHistoria(characterDTO.getHistoria());

        return updatedCharacter;

    }


    public CharacterBasicDTO entityToBasicDto(CharacterEntity characterEntity) {

        CharacterBasicDTO characterBasicDTO= new CharacterBasicDTO();
        characterBasicDTO.setImagen(characterEntity.getImagen());
        characterBasicDTO.setNombre(characterEntity.getNombre());

        return characterBasicDTO;
    }
}
