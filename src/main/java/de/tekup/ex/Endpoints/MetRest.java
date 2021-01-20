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

import de.tekup.ex.DTO.MetRequest;
import de.tekup.ex.DTO.MetResponse;
import de.tekup.ex.Models.Met;
import de.tekup.ex.Service.MetService;


@RestController
@RequestMapping("/api/mets")
public class MetRest {

    private MetService metService;

    @Autowired
    public MetRest(MetService metService) {
        super();
        this.metService = metService;
    }

    @GetMapping
    public List<Met> getAllMets()
    {
        return metService.getAllMets();
    }

    @GetMapping("/{nom}")
    public Met getMetByName(@PathVariable String nom)
    {
        return metService.getMetByName(nom);
    }

    @PostMapping
    public MetResponse createMet(@RequestBody@Valid MetRequest met)
    {
        return metService.createMet(met);
    }

    @PutMapping("/{nom}")
    public MetResponse modifyMet(@PathVariable String nom, @RequestBody@Valid MetRequest newMet)
    {
        return metService.modifyMet(nom,newMet);
    }

    @DeleteMapping("/{nom}")
    public MetResponse deleteMet(@PathVariable String nom)
    {
        return metService.deleteMetByName(nom);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }
}
