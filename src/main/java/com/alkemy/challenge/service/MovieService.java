package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.dto.MovieDTO;
import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.MovieEntity;
import com.alkemy.challenge.repository.CharacterRepository;
import com.alkemy.challenge.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CharacterRepository characterRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<MovieDTO> getAllMovies() {

        return movieRepository.findAll().stream().map(movieEntity -> modelMapper.map(movieEntity, MovieDTO.class)).collect(Collectors.toList());

    }

    public MovieDTO save(MovieDTO movieDTO) {
        MovieEntity movieEntity = modelMapper.map(movieDTO, MovieEntity.class);
        /*
        for (CharacterDTO character: movieDTO.getPersonajes()){
           //CharacterEntity characterEntity=characterRepository.findByNombreEquals(character.getNombre()).orElseThrow(() -> new RuntimeException("ERROR PERSONAJE INVÁLIDO"));
            CharacterEntity characterEntity=modelMapper.map(character,CharacterEntity.class);
            movieEntity.getPersonajes().add(characterEntity);
        }
*/
       movieRepository.save(movieEntity);
        return  movieDTO;
    }

    public MovieDTO update(MovieDTO movieDTO) {
      movieRepository.save(modelMapper.map(movieDTO,MovieEntity.class));
        return movieDTO;
    }


    public MovieDTO delete(MovieDTO movieDTO) {
        movieRepository.delete(modelMapper.map(movieDTO,MovieEntity.class));
        return movieDTO;
    }


}
