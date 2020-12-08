package com.rest.Mileapi.model.repository;

import com.rest.Mileapi.model.Cad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadRepository extends JpaRepository<Cad,Long> {
    boolean existsByNomeDono(String nomeDono);
}
