package com.dongyang.moviewreviewweb.moviereviewer.email;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification, String> {
}
