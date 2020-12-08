package com.rest.Mileapi.resource;


import com.rest.Mileapi.dto.CadDTO;
import com.rest.Mileapi.model.Cad;
import com.rest.Mileapi.service.CadService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.BindException;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class CadController {

    private CadService service;
    private ModelMapper modelMapper;

    public CadController(CadService service, ModelMapper mapper) {
        this.service = service;
        this.modelMapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadDTO create(@RequestBody @Valid CadDTO dto) {
        Cad entity = modelMapper.map(dto,Cad.class);
        entity = service.save(entity);
        return modelMapper.map(entity,CadDTO.class);
    }

    @GetMapping("{id}")
    public CadDTO get(@PathVariable long id){
        return service
                .getById(id)
                .map( cad-> modelMapper.map(cad, CadDTO.class))
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        Cad cad = service.getById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.delete(cad);
    }

    @PutMapping("{id}")
    public CadDTO update(@PathVariable long id, CadDTO dto){

        Cad cad = service.getById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
                cad.setNomeDono(dto.getNomeDono());
                cad.setNomePet(dto.getNomeDono());
                cad.setPorte(dto.getPorte());
                cad.setRaca(dto.getRaca());
                cad = service.update(cad);
                return modelMapper.map(cad, CadDTO.class);

    }

}
