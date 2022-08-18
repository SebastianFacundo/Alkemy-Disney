package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.dto.MovieDTO;
import com.alkemy.challenge.entity.CharacterEntity;
import com.alkemy.challenge.entity.GenderEntity;
import com.alkemy.challenge.entity.MovieEntity;
import com.alkemy.challenge.mapper.MovieMapper;
import com.alkemy.challenge.repository.CharacterRepository;
import com.alkemy.challenge.repository.GenderRepository;
import com.alkemy.challenge.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenderRepository genderRepository;
    MovieMapper movieMapper = new MovieMapper();

    public List<MovieDTO> getAllMovies() {

        List<MovieEntity> moviesEntity = movieRepository.findAll().stream().filter(movieEntity -> movieEntity.getBorrado().equals(Boolean.FALSE)).collect(Collectors.toList());
        List<MovieDTO> moviesDTO = moviesEntity.stream().map(movieEntity -> movieMapper.entityToDto(movieEntity)).collect(Collectors.toList());

        return moviesDTO;

    }

    public MovieDTO getMovie(Long id) {

        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if (movieEntity.isEmpty() || movieEntity.get().getBorrado()) {
            throw new RuntimeException("NO SE ENCONTRO ID");
        }

        MovieDTO movieDTO = movieMapper.entityToDto(movieEntity.get());

        return movieDTO;
    }

    public MovieDTO save(MovieDTO movieDTO) {
        Optional<MovieEntity> findMovie = movieRepository.findByTituloEquals(movieDTO.getTitulo());
        if (!findMovie.isEmpty()) {
            throw new RuntimeException("PELICULA YA EXISTE");
        }
        MovieEntity movieEntity = movieMapper.dtoToEntity(movieDTO);
        movieRepository.save(movieEntity);

        return movieMapper.entityToDto(movieEntity);
    }

    public MovieDTO update(MovieDTO movieDTO) {
        Optional<MovieEntity> findMovie = movieRepository.findById(movieDTO.getId());
        if (findMovie.isEmpty() || findMovie.get().getBorrado()) {
            throw new RuntimeException("NO SE ENCONTRO ID");
        }

        Optional<MovieEntity> findMovieByTitulo = movieRepository.findByTituloEquals(movieDTO.getTitulo());
        if ((!findMovieByTitulo.isEmpty()) && findMovieByTitulo.get().getId() != findMovie.get().getId()) {
            throw new RuntimeException("PELICULA YA INGRESADA");
        }
        MovieEntity movieEntity = movieMapper.refresh(movieDTO);
        movieEntity.setPersonajes(findMovie.get().getPersonajes());
        movieRepository.save(movieEntity);


        return movieMapper.entityToDto(movieEntity);
    }


    public MovieDTO delete(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("PERSONAJE INEXISTENTE"));
        movieEntity.setBorrado(Boolean.TRUE);
        movieRepository.save(movieEntity);

        return movieMapper.entityToDto(movieEntity);
    }

    public MovieBasicDTO getMovieForTitle(String title) {
        Optional<MovieEntity> findMovie = movieRepository.findByTituloEquals(title);
        if (findMovie.isEmpty() || findMovie.get().getBorrado()) {
            throw new RuntimeException("NO SE ENCONTRO LA PELÍCULA");
        }
        return movieMapper.entityToBasicDto(findMovie.get());
    }

    public List<MovieBasicDTO> getMoviesForIdGender(Long idGender) {


        Optional<GenderEntity> genderEntity= genderRepository.findById(idGender);
        List<Optional<MovieEntity>> moviesEntity = movieRepository.findByGeneroEquals(genderEntity.get()).stream().filter(movieEntity -> movieEntity.get().getBorrado() == Boolean.FALSE).collect(Collectors.toList());

        List<MovieBasicDTO> moviesBasicDTO = moviesEntity.stream().map(movieEntity -> movieMapper.entityToBasicDto(movieEntity.get())).collect(Collectors.toList());

        return moviesBasicDTO;
    }

    public List<MovieBasicDTO> getMoviesForOrder(String order) {

        List<Optional<MovieEntity>>moviesEntity= new ArrayList<>();

        if(order.toLowerCase().equals("asc")){
           moviesEntity = movieRepository.findByOrderByFechaCreacionAsc().stream().filter(movieEntity -> movieEntity.get().getBorrado() == Boolean.FALSE).collect(Collectors.toList());
        } else if (order.toLowerCase().equals("desc")) {
            moviesEntity = movieRepository.findByOrderByFechaCreacionDesc().stream().filter(movieEntity -> movieEntity.get().getBorrado() == Boolean.FALSE).collect(Collectors.toList());
        }


        List<MovieBasicDTO> movieBasicDTOS = moviesEntity.stream().map(movieEntity -> movieMapper.entityToBasicDto(movieEntity.get())).collect(Collectors.toList());

        return movieBasicDTOS;

    }


}
