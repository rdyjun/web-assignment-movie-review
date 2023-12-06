package com.dongyang.moviewreviewweb.moviereviewer.movie.service;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.Movie;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieList;
import com.dongyang.moviewreviewweb.moviereviewer.movie.movieapi.MovieAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImpl {
    public List<MovieList> getMovies (String sortBy) throws IOException {
        UriComponentsBuilder uri = MovieAPI.getUriBuilder();
        if (sortBy != null && sortBy.equals("rec"))
            uri.queryParam("sort_by", "primary_release_date.desc");
        return MovieAPI.jsonConvertToMovieList(MovieAPI.getAPIData(uri));
    }

    public String getMovieByMBTI(String mbti) {
        return MovieAPI.getAPIData(MovieAPI.getUriBuilder()
                .queryParam("with_genres", getMovieTypeByMBTI(mbti)));
    }
    public List<MovieList> getMovieByKeyword (String keyword) throws IOException {
        UriComponentsBuilder uri = MovieAPI.getSearchUriBuilder();
        uri.queryParam("query", keyword);
        return MovieAPI.jsonConvertToMovieList(MovieAPI.getAPIData(uri));
    }
    private String getMovieTypeByMBTI(String mbti) {
            switch (mbti) {
                case "INFP":
                    return "14"; // 판타지 장르 코드
                case "ENFP":
                    return "12"; // 어드벤처 장르 코드
                case "INFJ":
                    return "18"; // 드라마 장르 코드
                case "ENFJ":
                    return "10749"; // 로맨틱 코미디 장르 코드
                case "INTP":
                    return "878"; // 공상과학 장르 코드
                case "ENTP":
                    return "35"; // 블랙 코미디 장르 코드
                case "INTJ":
                    return "9648"; // 미스터리 장르 코드
                case "ENTJ":
                    return "53"; // 스릴러 장르 코드
                case "ISFJ":
                    return "10751"; // 교육 장르 코드
                case "ESFJ":
                    return "10770"; // 칙 플릭 장르 코드
                case "ISTJ":
                    return "80"; // 법정률 장르 코드
                case "ESTJ":
                    return "36"; // 역사 장르 코드
                case "ISFP":
                    return "16"; // 애니메이션 장르 코드
                case "ESFP":
                    return "10402"; // 뮤지컬 장르 코드
                case "ISTP":
                    return "99"; // 풍자 장르 코드
                case "ESTP":
                    return "28"; // 액션 장르 코드
                default:
                    throw new IllegalArgumentException("해당 mbti는 존재하지 않습니다 : " + mbti);
        }
    }
    public Movie getTargetMovie (String movieId) throws JsonProcessingException {
        UriComponentsBuilder builder = MovieAPI.getMovieTargetBuilder(movieId);
        String json = MovieAPI.getAPIData(builder);
        Movie movie = MovieAPI.jsonConvertToMovie(json).get(0);
        return movie;
    }
}