package de.tekup.ex.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.tekup.ex.Models.Client;





public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("select DAYNAME(t.date) as day from Ticket t "
            + "where t.client = :clt ")
    List<String> GetMostReservedDaysByClient(@Param("clt")Client client);

}
