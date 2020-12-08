package com.rest.Mileapi.service.implementations;

import com.rest.Mileapi.model.Cad;
import com.rest.Mileapi.model.repository.CadRepository;
import com.rest.Mileapi.service.CadService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadServiceImplementation implements com.rest.Mileapi.service.CadService {

    private CadRepository repository;


    public CadServiceImplementation(CadRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cad save(Cad cad) {
        return repository.save(cad);
    }

    @Override
    public Optional<Cad> getById(Long id) {
        return this.repository.findById(id);
    }

    @SneakyThrows
    @Override
    public void delete(Cad cad) {

        if(cad == null || cad.getId() == null)
            throw new IllegalAccessException("não pode ser null");
        this.repository.delete(cad);

    }

    @SneakyThrows
    @Override
    public Cad update(Cad cad) {
        if(cad == null || cad.getId() == null)
            throw new IllegalAccessException("não pode ser null");
        return this.repository.save(cad);
    }
}
