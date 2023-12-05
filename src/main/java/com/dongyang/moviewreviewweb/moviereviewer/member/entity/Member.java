package com.dongyang.moviewreviewweb.moviereviewer.member.entity;

public class Member {
    private String name;
    private String id;
    private String pw;
    private boolean status;
    public Member (String name, String id, String pw, boolean status) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }
}
