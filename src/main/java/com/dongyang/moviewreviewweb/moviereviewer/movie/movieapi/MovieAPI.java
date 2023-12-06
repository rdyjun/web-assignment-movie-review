package com.dongyang.moviewreviewweb.moviereviewer.movie.movieapi;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.Movie;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(builder.toUriString())
                .get()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYjVhZDM2YjlmZjBmOWU1ODZlNzU4NzI4ZmFhNWRkYyIsInN1YiI6IjY1NmM4MjVjMDg1OWI0MDExYzIyNmNkYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.78av1uah8E12AtrMQ5XcZmMPyLpDF66v543HCdQ-gm4")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.code() == 200)
                return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error: " + response.code();
    }
    public static UriComponentsBuilder getSearchUriBuilder () {
        String key = env.getProperty("movie.key");
        String url = env.getProperty("movie.search");
        return UriComponentsBuilder.fromUriString(url)
                .queryParam("api_key", key)
                .queryParam("region", "KR")
                .queryParam("include_adult", "false")
                .queryParam("language", "ko-KR");
    }
    public static UriComponentsBuilder getUriBuilder () {
        String key = env.getProperty("movie.key");
        String url = env.getProperty("movie.url");
        return UriComponentsBuilder.fromUriString(url)
                .queryParam("region", "KR")
                .queryParam("include_adult", "false")
                .queryParam("include_video", "false")
                .queryParam("language", "ko-KR")
                .queryParam("api_key", key)
                .queryParam("sort_by", "popularity.desc");
    }
    public static List<MovieList> jsonConvertToMovieList (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode moviesNode = root.path("results");
        List<MovieList> movies = new ArrayList<>();
        for (JsonNode movieNode : moviesNode) {
            MovieList movie = new MovieList();
            movie.setId(Long.parseLong(movieNode.path("id").asText()));
            movie.setTitle(movieNode.path("title").asText());
            movie.setPosterLink("https://image.tmdb.org/t/p/original/" + movieNode.path("poster_path").asText());
            movies.add(movie);
        }
        return movies;
    }
    public static List<Movie> jsonConvertToMovie (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode genres = root.path("genres");
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId(Long.parseLong(root.path("id").asText()));
        movie.setTitle(root.path("title").asText());
        for (JsonNode eachCategory : genres)
            movie.addCategory(eachCategory.path("name").asText());
        movie.setStatus(root.path("status").asText());
        movie.setRuntime(root.path("runtime").asText());
        movie.setReleaseDate(root.path("release_date").asText());
        movie.setOverView(root.path("overview").asText());
        movie.setPosterLink("https://image.tmdb.org/t/p/original/" + root.path("poster_path").asText());
        movie.setVideosLink(jsonConvertToVideosLink(getAPIData(getMovieVideosLinkBuilder(root.path("id").asText()))));
        movies.add(movie);
        return movies;
    }
    public static String jsonConvertToVideosLink (String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode moviesNode = root.path("results");
        String videosURL = null;
        for (JsonNode movieNode : moviesNode) {
            if (!movieNode.path("site").asText().equals("YouTube"))
                continue;
            videosURL = "https://www.youtube.com/embed/" + movieNode.path("key").asText();
            break;
        }
        return videosURL;
    }
    public static UriComponentsBuilder getMovieVideosLinkBuilder (String movieId) {
        String key = env.getProperty("movie.key");
        String url = env.getProperty("movie.target");
        return UriComponentsBuilder.fromUriString(url + movieId + "/videos")
                .queryParam("api_key", key);
    }
    public static UriComponentsBuilder getMovieTargetBuilder (String movieId) {
        String key = env.getProperty("movie.key");
        String url = env.getProperty("movie.target");
        return UriComponentsBuilder.fromUriString(url + movieId)
                .queryParam("api_key", key)
                .queryParam("language", "ko-KR");
    }
}
