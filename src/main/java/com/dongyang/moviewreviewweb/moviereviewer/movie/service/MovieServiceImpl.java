package com.dongyang.moviewreviewweb.moviereviewer.movie.service;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String getMovies (String sortBy) {
        UriComponentsBuilder uri = getUriBuilder();
        if (sortBy == null || sortBy.equals("pop"))
            return getAPIData(uri
                    .queryParam("sort_by", "popularity.desc"));
        return getAPIData(uri
                .queryParam("sort_by", "primary_release_date.desc"));
    }
    public String getMovieByMBTI(String mbti) {
        String[] category = new String[4];
        for (int i = 0; i < 4; i++) {
            category[i] = getMovieTypeByMBTI(mbti.charAt(i));
        }

        return getAPIData(getUriBuilder()
                .queryParam("with_genres", String.join(", ", category)));
    }

    private String getMovieTypeByMBTI(char mbti) {
        switch (mbti) {
            case 'I':
                return "28";  // Action for Introverted
            case 'E':
                return "12";  // Adventure for Extroverted
            case 'N':
                return "16";  // Animation for Intuitive
            case 'S':
                return "35";  // Comedy for Sensing
            case 'F':
                return "80";  // Crime for Feeling
            case 'T':
                return "99";  // Documentary for Thinking
            case 'J':
                return "18";  // Drama for Judging
            case 'P':
                return "10751";  // Family for Perceiving
            default:
                throw new IllegalArgumentException("Invalid personality type: " + mbti);
        }
    }
    public List<MovieDTO> jsonConvertToMovie (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode moviesNode = root.path("results");
        List<MovieDTO> movies = new ArrayList<>();
        for (JsonNode movieNode : moviesNode) {
            Long movieId = Long.parseLong(movieNode.path("id").asText());
            String movieTitle = movieNode.path("title").asText();
            String movieDirector = movieNode.path("title").asText();
            String releaseDate = movieNode.path("release_date").asText();
            String moviePoster = "https://image.tmdb.org/t/p/original/" + movieNode.path("poster_path").asText();
            MovieDTO movie = new MovieDTO(movieId, movieTitle, movieDirector, releaseDate, moviePoster);
            movies.add(movie);
        }
        return movies;
    }
    public UriComponentsBuilder getUriBuilder () {
        String key = env.getProperty("movie.key");
        String url = env.getProperty("movie.url");
        return UriComponentsBuilder.fromUriString(url)
                .queryParam("region", "KR")
                .queryParam("include_adult", "false")
                .queryParam("include_video", "false")
                .queryParam("language", "ko-KR")
                .queryParam("api_key", key);
    }
}