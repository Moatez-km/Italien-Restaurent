package de.tekup.ex.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.ex.Models.Table;

public interface TableRepository extends JpaRepository<Table, Integer>{

}
