package com.api.movies.services;

import com.api.movies.models.Movie;
import com.api.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Transactional
    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public Page<Movie> findAll(Pageable pageable){
        return movieRepository.findAll(pageable);
    }
}
