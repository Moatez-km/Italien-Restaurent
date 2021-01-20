package de.tekup.ex.Service;

import java.time.LocalDateTime;
import java.util.List;

import de.tekup.ex.DTO.MetResponse;
import de.tekup.ex.DTO.TicketRequest;
import de.tekup.ex.DTO.TicketResponse;
import de.tekup.ex.Models.Met;
import de.tekup.ex.Models.Ticket;


public interface TicketService {
	List<Ticket> getAllTickets();
    Ticket getTicketById(int numero);
    TicketResponse createTicket(TicketRequest ticket);
    TicketResponse modifyTicket(int numero,TicketRequest newTicket);
    TicketResponse deleteTicketById(int numero);

    Ticket addMeal(int numero, Met met);
    
  //The most ordered plate at a given Date (le plat le plus acheté dans une période "date" donnée)
    MetResponse getPlatByDate(LocalDateTime date);

   


}
