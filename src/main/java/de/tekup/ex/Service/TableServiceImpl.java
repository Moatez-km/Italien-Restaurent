package de.tekup.ex.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.ex.DTO.TableRequest;
import de.tekup.ex.DTO.TableResponse;
import de.tekup.ex.Models.Table;
import de.tekup.ex.Models.Ticket;
import de.tekup.ex.Repositories.TableRepository;
import de.tekup.ex.Repositories.TicketRepository;
@Service
public class TableServiceImpl implements TableService {
	private TableRepository tableRepository;
    private TicketRepository ticketRepo;
    private ModelMapper mapper = new ModelMapper();
    @Autowired
    public TableServiceImpl(TableRepository tableRepository,TicketRepository ticketRepo) {
        super();
        this.tableRepository = tableRepository;
        this.ticketRepo = ticketRepo;
    }

	@Override
	public List<Table> getAllTables() {
		// TODO Auto-generated method stub
		return tableRepository.findAll();
	}

	@Override
	public Table getTableById(int numero) {
		// TODO Auto-generated method stub
		Optional<Table> opt = tableRepository.findById(numero);
        Table table;
        if (opt.isPresent())
        {
            table = opt.get();
        }
        else
        {
            throw new NoSuchElementException("il n'y a pas une table avec le numero saisi");
        }
        return table;
	}

	@Override
	public TableResponse createTable(TableRequest table) {
		// TODO Auto-generated method stub
		 Table tableRequest = mapper.map(table, Table.class);
	        tableRepository.save(tableRequest);
	        return mapper.map(tableRequest, TableResponse.class);
	}

	@Override
	public TableResponse modifyTable(int numero, TableRequest newTable) {
		// TODO Auto-generated method stub
		 Table tableRequest = mapper.map(newTable, Table.class);
	        Table thisTable = this.getTableById(numero);
	        if (tableRequest.getNbCouvert()!=0)
	        {
	            thisTable.setNbCouvert(tableRequest.getNbCouvert());
	        }
	        if (tableRequest.getSupplement()>=0)
	        {
	            thisTable.setSupplement(tableRequest.getSupplement());
	        }
	        if (tableRequest.getType()!= null)
	        {
	            thisTable.setType(tableRequest.getType());
	        }
	        tableRepository.save(thisTable);
	        return mapper.map(thisTable, TableResponse.class);
	}

	@Override
	public TableResponse deleteTableById(int numero) {
		// TODO Auto-generated method stub
		Table table = this.getTableById(numero);
        tableRepository.deleteById(numero);
        return mapper.map(table, TableResponse.class);
	}

	@Override
	public Table addTicket(int numero, Ticket ticket) {
		// TODO Auto-generated method stub
		 Table table = this.getTableById(numero);
	        table.getTickets().add(ticket);
	        table.setNbCouvert(ticket.getNbCouvert());

	        //making the reference with the ticket entity
	        ticket.setAddition(ticket.getAddition()+table.getSupplement());
	        ticket.setTable(table);

	        //saving the changes
	        ticketRepo.save(ticket);
	        return tableRepository.save(table);
	}

	@Override
	public TableResponse getMostReservedTable() {
		// TODO Auto-generated method stub
		List<Table> tables = tableRepository.findAll();
        Table saveTable = null;
        int max = 0;
        for (Table table : tables)
        {
            if (table.getTickets().size() > max)
            {
                max = table.getTickets().size();
                saveTable = table;
            }
        }
        return mapper.map(saveTable,TableResponse.class);
	}

}
