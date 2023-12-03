package com.dongyang.moviewreviewweb.moviereviewer.movie.service;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.Movie;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl {
    private static Environment env;
    @Autowired
    public MovieServiceImpl(Environment env) {
        MovieServiceImpl.env = env;
    }
    public String getAPIData (UriComponentsBuilder builder) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        if (response.getStatusCodeValue() == 200)
            return response.getBody();

        return "Error: " + response.getStatusCodeValue();
    }
    public String getTrendingMovies () {
        String TrendingMovieURL = env.getProperty("movie.trend.url");
        String APIKey = env.getProperty("movie.key");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TrendingMovieURL + "?key=" + APIKey)
                .queryParam("itemPerPage", 5)
                .queryParam("repNationCd", "K");
        return getAPIData(builder);
    }
    public String getMovieByMBTI(String mbti) {
        String movieType = getMovieTypeByMBTI(mbti);
        String TrendingMovieURL = env.getProperty("movie.url");
        String APIKey = env.getProperty("movie.key");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TrendingMovieURL + "?key=" + APIKey)
                .queryParam("repNationCd", "K")
                .queryParam("query_term", movieType)
                .queryParam("limit", 20);

        return getAPIData(builder);
    }

    private String getMovieTypeByMBTI(String mbti) {
        switch (mbti) {
            case "S":
                return "Documentary";
            case "N":
                return "Fantasy";
            case "T":
                return "Mystery";
            case "F":
                return "Romance";
            case "J":
                return "Drama";
            case "P":
                return "Adventure";
            default:
                return "All";
        }
    }
    public List<MovieDTO> jsonConvertToMovie (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode moviesNode = root.path("data").path("movies");
        List<MovieDTO> movies = new ArrayList<>();
        for (JsonNode movieNode : moviesNode) {
            Long movieId = Long.parseLong(movieNode.path("id").asText());
            String movieTitle = movieNode.path("movieNm").asText();
            String movieDirector = movieNode.path("director").asText();
            int releaseDate = Integer.parseInt(movieNode.path("openDt").asText());
            String moviePoster = movieNode.path("medium_cover_image").asText();
            MovieDTO movie = new MovieDTO(movieId, movieTitle, movieDirector, releaseDate, moviePoster);
            movies.add(movie);
        }
        return movies;
    }
}