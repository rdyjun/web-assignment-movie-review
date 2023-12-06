package com.dongyang.moviewreviewweb.moviereviewer.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private List<String> category;
    private String status;
    private String runtime;
    private String releaseDate;
    private String posterLink;
    private String overView;
    private String videosLink;

    public void addCategory(String category) {
        if (this.category == null)
            this.category = new ArrayList<>();
        this.category.add(category);
    }
}
