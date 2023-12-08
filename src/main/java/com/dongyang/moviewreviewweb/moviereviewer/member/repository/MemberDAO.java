package com.dongyang.moviewreviewweb.moviereviewer.member.repository;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberMapper;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO implements MemberRepository {
    @Autowired
    private DBConnector dbConnector;
    @Override
    public Optional<Member> findById (String id) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Optional<Member> member = null;
        String sql = "SELECT * FROM member WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                member = Optional.ofNullable(MemberMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (member == null)
            return Optional.empty();
        return member;
    }
    @Override
    public Optional<Member> findByUserName(String name) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        String sql = "SELECT * FROM member WHERE userName = ?";
        Optional<Member> member = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next())
                member = Optional.ofNullable(MemberMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (member == null)
            return Optional.empty();
        return member;
    }

    @Override
    public boolean save(Register register) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "INSERT INTO member(userName, id, pw) VALUES (?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, register.getName());
            pstmt.setString(2, register.getId());
            pstmt.setString(3, register.getPw());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Optional<Member> findByIdAndPw (String id, String pw) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Optional<Member> member = null;
        String sql = "SELECT * FROM member WHERE id = ? and pw = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            if (rs.next())
                member = Optional.ofNullable(MemberMapper.mapToEntity(rs));

            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (member == null)
            return Optional.empty();
        return member;
    }
    @Override
    public List<Member> findAll () {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Member member;
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member";
        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                member = MemberMapper.mapToEntity(rs);
                memberList.add(member);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberList;
    }
    @Override
    public void removeById (String id) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM member WHERE id = ?;";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Member> findByBlackList (boolean status) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Member member;
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE status = ?;";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, status);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                member = MemberMapper.mapToEntity(rs);
                memberList.add(member);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberList;
    }
    @Override
    public void updateBlockByIdAndStatus (String id, boolean status) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "UPDATE member SET status = ? WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, status);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean findByIdAtStatus (String memberId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        String sql = "SELECT * FROM member WHERE id = ?;";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            rs.next();
            boolean status = rs.getBoolean("status");
            rs.close();
            pstmt.close();
            connection.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void updateMemberNameByMemberId (String memberId, String newName) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "UPDATE member SET userName = ? WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
