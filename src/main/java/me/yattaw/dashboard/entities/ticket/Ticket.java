package me.yattaw.dashboard.entities.ticket;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject")
    @Size(min = 4, max = 256, message = "subject must be between 4 and 256 characters")
    private String subject;

    @Column(name = "opened")
    private boolean opened;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private List<TicketResponse> responses;

    public LocalDateTime getLastResponse() {
        return responses.get(responses.size() - 1).getCreatedAt();
    }


}