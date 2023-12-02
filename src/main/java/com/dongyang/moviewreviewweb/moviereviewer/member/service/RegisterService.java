package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.RegisterDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface RegisterService {
    void register (RegisterDTO registerDTO) throws SQLException, ClassNotFoundException;
    boolean validateUserData (RegisterDTO registerDTO, Connection connection) throws SQLException;
    boolean save (RegisterDTO registerDTO, Connection connection) throws SQLException;
}
