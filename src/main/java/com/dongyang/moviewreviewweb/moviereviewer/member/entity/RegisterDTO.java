package com.dongyang.moviewreviewweb.moviereviewer.member.entity;

public class RegisterDTO {
    private String name;
    private String id;
    private String pw;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
