package de.tekup.ex.Models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Ticket {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;
    private LocalDateTime date;
    private int nbCouvert;
    private float addition;
    
    @JsonIgnore
    @ManyToOne
    private Client client;
    
    @ManyToOne
    @JsonIgnore
    private Table table;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable (name = "ticket_composer", joinColumns = @JoinColumn (name = "numero"),
            inverseJoinColumns = @JoinColumn(name = "nom"))
    private Set<Met> mets = new HashSet<>();

}
