package me.yattaw.dashboard.entities.ticket;

import lombok.Data;
import me.yattaw.dashboard.entities.UserEntity;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ticket_response")
public class TicketResponseEntity {

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    private TicketEntity ticketEntity;

    @ManyToOne
    @JoinColumn(name = "tickets_id", referencedColumnName = "ticket_id")
    private TicketEntity tickets;

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
