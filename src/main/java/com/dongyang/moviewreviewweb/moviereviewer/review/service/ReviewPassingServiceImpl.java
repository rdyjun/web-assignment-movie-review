package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewPassingServiceImpl implements ReviewPassingService {
    private Element element;
    @Value("${movie.url}")
    private String APIURL;

//    public ReviewPassingServiceImpl() throws IOException, ParserConfigurationException, SAXException {
//        dbConnector = new DBConnector();
//        MovieListAPI movieListAPI = new MovieListAPI();
//        element = movieListAPI.getAPI();
//        movieListAPI.getMovieData(element, "rank");
//    }

    @Override
    public void getReviewData (Long movieCode) {
    }
    @Override
    public List<String> getRank () {
        NodeList nodeList = element.getElementsByTagName("rank");
        List<String> rank = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node rankNode = nodeList.item(i);
            rank.add(rankNode.getTextContent());
        }
        return rank;
    }
}
