package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class DeleteAccountServiceImpl implements DeleteAccountService {
    @Override
    public void deleteAccount(HttpSession httpSession) throws SQLException, ClassNotFoundException {
        String userId = (String) httpSession.getAttribute("userId");
        Connection conn = DBConnector.getConnect();
        deleteAccountAtDB(userId, conn);
        deleteReviewAtAccount(userId, conn);
        conn.close();
    }
    @Override
    public void deleteAccountAtDB (String userId, Connection conn) throws SQLException {
        PreparedStatement pstmt;
        String sql = "DELETE FROM member WHERE id = ?;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userId);
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public int deleteReviewAtAccount(String userId, Connection conn) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        // 유저 총 리뷰 작성 수 확인
        String showUserReviewCount = "SELECT COUNT(*) FROM review WHERE id = ?;";
        pstmt = conn.prepareStatement(showUserReviewCount);
        pstmt.setString(1, userId);
        rs = pstmt.executeQuery();
        int result = 0;
        if (rs.next())
            result = rs.getInt(1);
        rs.close();
        // 리뷰 삭제
        String removeUserReviews = "DELETE FROM review WHERE id = ?;";
        pstmt = conn.prepareStatement(removeUserReviews);
        pstmt.setString(1, userId);
        pstmt.executeUpdate();
        pstmt.close();
        return result;
    }
}
