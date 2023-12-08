package com.dongyang.moviewreviewweb.moviereviewer.member.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper {
    public static Member mapToEntity (Register register) {
        return new Member(register.getName(), register.getName(), register.getPw(), false);
    }
    public static Member mapToEntity (ResultSet resultSet) {
        Member member = null;
        try {
            member = new Member(resultSet.getString("name"),
                    resultSet.getString("id"),
                    resultSet.getString("pw"),
                    resultSet.getBoolean("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}
