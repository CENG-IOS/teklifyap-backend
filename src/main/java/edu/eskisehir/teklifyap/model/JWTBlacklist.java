package edu.eskisehir.teklifyap.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "jwt_blacklist")
public class JWTBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    @Column(name = "time_stamp")
    private LocalDateTime timestamp;
    public JWTBlacklist(String token) {
        this.token = token;
        timestamp = LocalDateTime.now();
    }
}
