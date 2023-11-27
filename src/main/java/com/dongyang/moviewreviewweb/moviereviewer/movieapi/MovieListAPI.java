package com.dongyang.moviewreviewweb.moviereviewer.movieapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Component
public class MovieListAPI {
    @Value("${movie.url}")
    private String APIURL;
    public String getAPI () {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(APIURL, String.class);
        return result;
    }
//    public String getMovieData (Element APIElement, String findKey) {
//        NodeList nodes = APIElement.getElementsByTagName(findKey);
//        String findValue;
//        if (nodes.getLength() > 0) {
//            Node findNode = nodes.item(0);
//            findValue = findNode.getTextContent();
//            System.out.println(findValue);
//            return findValue;
//        }
//        return findKey;
//    }
}
