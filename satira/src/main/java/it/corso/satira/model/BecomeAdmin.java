package it.corso.satira.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BecomeAdmin {
    private String username;
    private String email;
    private String token;
    private boolean confirmed = false;
}
