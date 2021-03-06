package de.tekup.ex.Endpoints;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.ex.DTO.MetResponse;
import de.tekup.ex.DTO.TicketRequest;
import de.tekup.ex.DTO.TicketResponse;
import de.tekup.ex.Models.Met;
import de.tekup.ex.Models.Ticket;
import de.tekup.ex.Service.TicketService;



@RestController
@RequestMapping("/api/tickets")
public class TicketRest {

    private TicketService ticketService;

    @Autowired
    public TicketRest(TicketService ticketService) {
        super();
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets()
    {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{numero}")
    public Ticket getTicketById(@PathVariable int numero) 
    {
        return ticketService.getTicketById(numero);
    }

    @PostMapping
    public TicketResponse createTicket(@RequestBody@Valid TicketRequest ticket)
    {
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{numero}")
    public TicketResponse modifyTicket(@PathVariable("numero") int numero, @RequestBody@Valid TicketRequest newTicket)
    {
        return ticketService.modifyTicket(numero,newTicket);
    }

    @DeleteMapping("/{numero}")
    public TicketResponse deleteTicketById(@PathVariable("numero") int numero)
    {
        return ticketService.deleteTicketById(numero);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e)
    {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addMeal/{numero}")
    public Ticket addMeal(@PathVariable int numero, @RequestBody@Valid Met met)
    {
        return ticketService.addMeal(numero,met);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/Plats/{date}")
    public MetResponse getPlatByDate(@PathVariable String date)
    {
        LocalDateTime buyingDate = LocalDateTime.parse(date);
        return ticketService.getPlatByDate(buyingDate);
    }
   
}
