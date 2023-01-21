package me.yattaw.dashboard.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity()
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 48)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 16)
    private String username;

}