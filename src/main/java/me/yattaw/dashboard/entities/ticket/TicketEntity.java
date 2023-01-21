package me.yattaw.dashboard.entities.ticket;

import jakarta.persistence.*;
import lombok.Data;
import me.yattaw.dashboard.entities.UserEntity;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private long id;

    @Column(name = "ticket_title", nullable = false)
    private String subject;

    @Column(name = "ticket_opened")
    private boolean opened;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "response_date", nullable = false)
    private Date responseDate;

    @OneToMany(mappedBy = "tickets")
    @Column(name = "ticket_messages")
    private Set<TicketResponseEntity> ticketMessages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

}