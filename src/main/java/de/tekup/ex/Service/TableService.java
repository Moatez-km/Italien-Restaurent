package de.tekup.ex.Service;

import java.util.List;

import de.tekup.ex.DTO.TableRequest;
import de.tekup.ex.DTO.TableResponse;
import de.tekup.ex.Models.Table;
import de.tekup.ex.Models.Ticket;



public interface TableService {
	  List<Table> getAllTables();
	    Table getTableById(int numero);
	    TableResponse createTable(TableRequest table);
	    TableResponse modifyTable(int numero,TableRequest newTable);
	    TableResponse deleteTableById(int numero);

	    Table addTicket(int numero, Ticket ticket);
	    TableResponse getMostReservedTable();

}
