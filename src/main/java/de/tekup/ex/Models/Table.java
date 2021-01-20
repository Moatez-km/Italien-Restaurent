package de.tekup.ex.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;

@Data
@Entity(name = "TableRestaurant")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    private int nbCouvert;
    private String type;
    private float supplement;

    @OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
    private Set<Ticket> tickets = new HashSet<>();

}