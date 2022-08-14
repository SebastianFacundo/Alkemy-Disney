package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.CharacterDTO;

import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.MovieEntity;

import java.util.Set;

public class CharacterMapper {

    public CharacterEntity dtoToEntity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setId(dto.getId());
        characterEntity.setImagen(dto.getImagen());
        characterEntity.setNombre(dto.getNombre());
        characterEntity.setEdad(dto.getEdad());
        characterEntity.setPeso(dto.getPeso());
        characterEntity.setHistoria(dto.getHistoria());

        Set<MovieEntity> peliculas;




        return characterEntity;
    }



}
