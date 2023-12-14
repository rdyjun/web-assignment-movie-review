package com.dongyang.moviewreviewweb.moviereviewer.email;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Verification {
    @Id
    private String email;
    private String verify_key;

    public boolean isCertified (String verify_key) {
        return this.verify_key.equals(verify_key);
    }
}
