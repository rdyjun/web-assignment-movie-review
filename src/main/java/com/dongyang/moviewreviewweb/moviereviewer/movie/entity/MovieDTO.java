package com.dongyang.moviewreviewweb.moviereviewer.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String title;
    private String director;
    private String releaseDate;
    private String posterLink;
}
