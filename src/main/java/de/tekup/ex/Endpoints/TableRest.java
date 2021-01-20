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

import de.tekup.ex.DTO.TableRequest;
import de.tekup.ex.DTO.TableResponse;
import de.tekup.ex.Models.Table;
import de.tekup.ex.Models.Ticket;
import de.tekup.ex.Service.TableService;



@RestController
@RequestMapping("/api/tables")
public class TableRest {

    private TableService tableService;

    @Autowired
    public TableRest(TableService tableService) {
        super();
        this.tableService = tableService;
    }

    @GetMapping
    public List<Table> getAllTables()
    {
        return tableService.getAllTables();
    }

    @GetMapping("/{numero}")
    public Table getTableById(@PathVariable int numero)
    {
        return tableService.getTableById(numero);
    }

    @PostMapping
    public TableResponse creatTable(@RequestBody@Valid TableRequest table)
    {
        return tableService.createTable(table);
    }

    @PutMapping("/{numero}")
    public TableResponse modifyTable(@PathVariable int numero, @RequestBody@Valid TableRequest newTable)
    {
        return tableService.modifyTable(numero,newTable);
    }

    @DeleteMapping("/{numero}")
    public TableResponse deleteTable(@PathVariable int numero)
    {
        return tableService.deleteTableById(numero);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e)
    {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addTicket/{numero}")
    public Table addTicketToTable(@PathVariable int numero, @RequestBody@Valid Ticket ticket)
    {
        return tableService.addTicket(numero,ticket);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/mostReservedTable")
    public TableResponse getMostReservedTable()
    {
        return tableService.getMostReservedTable();
    }
}
