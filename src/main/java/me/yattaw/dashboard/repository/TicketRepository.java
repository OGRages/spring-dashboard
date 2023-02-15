package me.yattaw.dashboard.repository;

import me.yattaw.dashboard.entities.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByCreatedBy(String createdBy);

}
