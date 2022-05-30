package com.api.movies.controller;

import com.api.movies.dtos.MovieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllMoviesTest() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk());
    }

    @Test
    void registerMovieTest() throws Exception {
        var movie = new MovieDto();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-fi");
        movie.setReleaseYear("2014");
        mockMvc.perform(post("/movies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isCreated());
    }

}