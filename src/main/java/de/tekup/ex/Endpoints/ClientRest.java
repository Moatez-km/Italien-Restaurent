package de.tekup.ex.Endpoints;

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



import de.tekup.ex.DTO.ClientRequest;
import de.tekup.ex.DTO.ClientResponse;
import de.tekup.ex.Models.Client;
import de.tekup.ex.Models.Ticket;
import de.tekup.ex.Service.ClientService;
import de.tekup.ex.Service.TicketService;



@RestController
@RequestMapping("/api/clients")
public class ClientRest {

    private ClientService clientService;
    private TicketService ticketService;
    


    @Autowired
    public ClientRest(ClientService clientService, TicketService ticketService) {
        super();
        this.clientService = clientService;
        this.ticketService = ticketService;
        
    }

    @GetMapping
    public List<Client> getAll()
    {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable long id)
    {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ClientResponse createClient(@RequestBody ClientRequest client)
    {
        return clientService.createClient(client);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e)
    {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ClientResponse modifyClient(@PathVariable("id") long id, @RequestBody ClientRequest newClient) {
        return clientService.modifyClient(id, newClient);
    }

    @DeleteMapping("/{id}")
    public ClientResponse deleteById(@PathVariable("id") long id) {
        return clientService.deleteClientById(id);
    }

    @PostMapping("/addTicket/{id}")
    public Client addTicketToClient(@PathVariable long id, @RequestBody@Valid Ticket ticket)
    {
        return clientService.addTicket(id,ticket);
    }

    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/plusFideleClient")
    public ClientResponse MostLoyalClient()
    {
        return clientService.getFideleClient();
    }

    @GetMapping("/PlusReserverDay")
    public String getMostReservedDayByClient(@RequestBody Client client)
    {
        return clientService.getPlusResrvedByClient(client);
    }
}
