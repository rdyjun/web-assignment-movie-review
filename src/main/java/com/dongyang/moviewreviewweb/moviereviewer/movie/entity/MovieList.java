package com.dongyang.moviewreviewweb.moviereviewer.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieList {
    private Long id;
    private String title;
    private String posterLink;
}
