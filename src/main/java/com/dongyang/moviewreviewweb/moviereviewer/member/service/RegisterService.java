package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Register;

import java.sql.SQLException;

public interface RegisterService {
    void register (Register register) throws SQLException, ClassNotFoundException;
}
