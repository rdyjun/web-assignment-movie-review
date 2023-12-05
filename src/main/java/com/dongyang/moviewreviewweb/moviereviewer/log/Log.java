package com.dongyang.moviewreviewweb.moviereviewer.log;

import java.sql.Timestamp;

public class Log {
    private Timestamp time;
    private String content;
    private String author;
    public Log (String content, String author) {
        this.content = content;
        this.author = author;
    }
    public Log (Timestamp time, String content, String author) {
        this.time = time;
        this.content = content;
        this.author = author;
    }
    public Timestamp getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
