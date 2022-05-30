package com.api.movies.controller;

import com.api.movies.dtos.MovieDto;
import com.api.movies.models.Movie;
import com.api.movies.services.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<Object> registerMovie(@RequestBody @Valid MovieDto movieDto){
        var movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);
        movie.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movie));
    }

    @GetMapping
    public ResponseEntity<Page<Movie>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMovie(@PathVariable(value = "id") Long id){
        Optional<Movie> movieOptional = movieService.getById(id);
        if (movieOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable(value = "id") Long id){
        Optional<Movie> movieOptional = movieService.getById(id);
        if (movieOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        movieService.deleteMovie(movieOptional.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable(value = "id")Long id, @RequestBody @Valid MovieDto movieDto){
        Optional<Movie> movieOptional = movieService.getById(id);
        if (movieOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);
        movie.setRegistrationDate(movieService.getById(id).get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movie));
    }

}
