package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.RegisterDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Override
    public void register(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException {
        Connection connect = DBConnector.getConnect();
        validateUserData(registerDTO, connect);
        save(registerDTO, connect);
    }

    @Override
    public boolean validateUserData(RegisterDTO registerDTO, Connection connection) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        String sql = "SELECT * FROM member WHERE id = ?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, registerDTO.getId());
        rs = pstmt.executeQuery();
        if (rs.next())
            throw new IllegalArgumentException();
        return true;
    }

    @Override
    public boolean save(RegisterDTO registerDTO, Connection connection) throws SQLException {
        PreparedStatement pstmt;
        String sql = "INSERT INTO member VALUES (?, ?, ?)";
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, registerDTO.getName());
        pstmt.setString(2, registerDTO.getId());
        pstmt.setString(3, registerDTO.getPw());
        pstmt.executeUpdate();

        return true;
    }
}
