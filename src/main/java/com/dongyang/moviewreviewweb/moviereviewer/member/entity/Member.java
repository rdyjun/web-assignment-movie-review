package com.dongyang.moviewreviewweb.moviereviewer.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String name;
    private String id;
    private String pw;
    private boolean status;
}
