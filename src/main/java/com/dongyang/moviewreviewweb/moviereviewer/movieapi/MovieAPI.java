package com.dongyang.moviewreviewweb.moviereviewer.movieapi;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieAPI {
    private static Environment env;
    @Autowired
    public MovieAPI(Environment env) {
        MovieAPI.env = env;
    }
    public static String getAPIData(UriComponentsBuilder builder) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        if (response.getStatusCodeValue() == 200)
            return response.getBody();

        return "Error: " + response.getStatusCodeValue();
    }
    public static UriComponentsBuilder getUriBuilder () {
        String key = env.getProperty("movie.key");
        String url = env.getProperty("movie.url");
        return UriComponentsBuilder.fromUriString(url)
                .queryParam("region", "KR")
                .queryParam("include_adult", "false")
                .queryParam("include_video", "false")
                .queryParam("language", "ko-KR")
                .queryParam("api_key", key);
    }
    public static List<MovieDTO> jsonConvertToMovie (String json) throws JsonProcessingException {
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
}
