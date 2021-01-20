package de.tekup.ex.Service;

import java.util.List;



import de.tekup.ex.DTO.ClientRequest;
import de.tekup.ex.DTO.ClientResponse;
import de.tekup.ex.Models.Client;
import de.tekup.ex.Models.Ticket;


public interface ClientService {
	List<Client> getAllClients();
    Client getClientById(long id);
    ClientResponse createClient(ClientRequest client);
    ClientResponse modifyClient(long id,ClientRequest newclient);
    ClientResponse deleteClientById(long id);
    
    Client addTicket(long id, Ticket ticket);

    


    ClientResponse getFideleClient();

    String getPlusResrvedByClient(Client client);


}
