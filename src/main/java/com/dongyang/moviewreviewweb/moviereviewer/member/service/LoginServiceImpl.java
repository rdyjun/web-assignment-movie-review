package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.LoginDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public void login (LoginDTO loginData, HttpSession httpSession) throws SQLException, ClassNotFoundException {
        if(validateAccount(loginData))
            throw new NoSuchElementException("회원 정보가 존재하지 않습니다.");
        httpSession.setAttribute("userId", loginData.getId());
    }
    public boolean validateAccount (LoginDTO loginData) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnector.getConnect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isValid = true;
        String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, loginData.getId());
        pstmt.setString(2, loginData.getPw());
        rs = pstmt.executeQuery();
        isValid = !rs.next();
        pstmt.close();
        conn.close();
        return isValid;
    }
}
