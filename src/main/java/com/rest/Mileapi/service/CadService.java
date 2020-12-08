package com.rest.Mileapi.service;

import com.rest.Mileapi.model.Cad;

import java.util.Optional;

public interface CadService {

    Cad save(Cad any);

    Optional<Cad> getById(Long id);

    void delete(Cad cad);

    Cad update(Cad cad);
}
