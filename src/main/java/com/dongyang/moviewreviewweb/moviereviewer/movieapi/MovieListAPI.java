package com.dongyang.moviewreviewweb.moviereviewer.movieapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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

    @Value("${movie.key}")
    private String APIKey;
    public Element getAPI () throws IOException, ParserConfigurationException, SAXException {
        String url = this.APIURL + "?key=" + this.APIKey;
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(url);
        return document.getDocumentElement();
    }
    public String getMovieData (Element APIElement, String findKey) {
        NodeList nodes = APIElement.getElementsByTagName(findKey);
        String findValue;
        if (nodes.getLength() > 0) {
            Node findNode = nodes.item(0);
            findValue = findNode.getTextContent();
            System.out.println(findValue);
            return findValue;
        }
        return findKey;
    }
}
