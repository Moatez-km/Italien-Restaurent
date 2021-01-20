package de.tekup.ex.Models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Client {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    @Column(nullable = true)
    private String courriel;
    @Column(nullable = true)
    private String telephone;
    
    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
    private Set<Ticket> tickets = new HashSet<>();

}
