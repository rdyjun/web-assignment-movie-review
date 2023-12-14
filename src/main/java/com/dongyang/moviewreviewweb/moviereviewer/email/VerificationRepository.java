package com.dongyang.moviewreviewweb.moviereviewer.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
@Transactional
public interface VerificationRepository extends JpaRepository<Verification, String> {
    @Modifying
    @Query("delete from Verification v where v.duration < :timestamp")
    void deleteAllBeforeTimestamp(@Param("timestamp") Timestamp timestamp);
}
