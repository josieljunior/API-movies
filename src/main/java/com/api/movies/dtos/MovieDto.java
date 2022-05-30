package com.api.movies.dtos;

import javax.validation.constraints.NotBlank;

public class MovieDto {

    @NotBlank
    private String title;
    @NotBlank
    private String genre;
    @NotBlank
    private String releaseYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}
