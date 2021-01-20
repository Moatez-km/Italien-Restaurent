package de.tekup.ex.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.ManyToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class Met {

    @Id
    private String nom;
    private float prix;
    @Column(nullable = false)
    private String type = "met";

    @JsonIgnore
    @ManyToMany(mappedBy = "mets")
    private Set<Ticket> tickets = new HashSet<>();
    
   

}
