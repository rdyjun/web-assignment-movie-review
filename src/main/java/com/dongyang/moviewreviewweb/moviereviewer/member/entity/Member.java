package com.dongyang.moviewreviewweb.moviereviewer.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private String id;
    private String name;
    private String pw;
    private boolean status;

    public boolean isBlocked() {
        return status;
    }

    public void block() {
        status = true;
    }

    public void unblock() {
        status = false;
    }

    public void modifyName(String newName) {
        name = newName;
    }
}
