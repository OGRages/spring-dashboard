package me.yattaw.dashboard.entities.ticket;

import jakarta.persistence.*;
import me.yattaw.dashboard.entities.UserEntity;

import java.util.Date;

public class TicketResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id", nullable = false)
    private long id;

    @Column(name = "response_date", nullable = false)
    private Date responseDate;

    @Column(name = "response_details", nullable = false, length = 3000)
    private String responseDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

}
