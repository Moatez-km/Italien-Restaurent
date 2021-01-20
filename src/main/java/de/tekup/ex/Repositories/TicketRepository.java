package de.tekup.ex.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.tekup.ex.Models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
}
