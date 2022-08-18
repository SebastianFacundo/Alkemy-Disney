package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.dto.MovieDTO;
import com.alkemy.challenge.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
   // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {

        List<MovieDTO> response =movieService.getAllMovies();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieDTO> getDetailsMovie(@PathVariable("id") Long id) {

       MovieDTO response = movieService.getMovie(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO) {

       MovieDTO response = movieService.save(movieDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MovieDTO> updateMovie(@RequestBody MovieDTO MovieDTO) {

       MovieDTO response = movieService.update(MovieDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<MovieDTO>deleteMovie(@PathVariable("id") Long id) {

      MovieDTO response = movieService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "title")
    public ResponseEntity<MovieBasicDTO> getForTitle(@RequestParam("title") String title) {

        MovieBasicDTO response= movieService.getMovieForTitle(title);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "genre")
    public ResponseEntity<List<MovieBasicDTO>> getForGender(@RequestParam("genre") Long idGender) {

        List<MovieBasicDTO> response= movieService.getMoviesForIdGender(idGender);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "order")
    public ResponseEntity<List<MovieBasicDTO>> getForOrder(@RequestParam("order") String order) {

        List<MovieBasicDTO> response= movieService.getMoviesForOrder(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
