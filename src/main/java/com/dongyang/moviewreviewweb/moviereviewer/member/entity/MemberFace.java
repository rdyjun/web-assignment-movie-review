package com.dongyang.moviewreviewweb.moviereviewer.member.entity;

public class MemberFace {
    private String id;
    private String name;
    public MemberFace(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberFace(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
