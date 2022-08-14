package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.MovieDTO;
import com.alkemy.challenge.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;
    private MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDTO> getAllMovies(){

        return movieService.getAllMovies();
    }

    @PostMapping
    public MovieDTO addMovie (@RequestBody MovieDTO movieDTO){

        return movieService.save(movieDTO);
    }
}
