package com.restapi.milesimus.service;

import com.restapi.milesimus.exception.ResourceNotFoundException;
import com.restapi.milesimus.model.CadPet;
import com.restapi.milesimus.repository.CadPetRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CadPetServiceImpl implements CadPetService {


    @Autowired
    private CadPetRepositorio cadPetRepositorio;



    @Override
    public CadPet createProduto(CadPet cadPet) {
        return cadPetRepositorio.save(cadPet);
    }

    @Override
    public CadPet updateProduto(CadPet cadPet) {
        Optional<CadPet> produtoDb=this.cadPetRepositorio.findById(cadPet.getId());

        if(produtoDb.isPresent()){
            CadPet cadPetUpdate = produtoDb.get();
            cadPetUpdate.setId(cadPet.getId());
            cadPetUpdate.setNome(cadPet.getNome());
            cadPetUpdate.setRaca(cadPet.getRaca());
            cadPetUpdate.setTipo_estadia(cadPetUpdate.getTipo_estadia());
            cadPetUpdate.setPorte(cadPetUpdate.getPorte());
            cadPetUpdate.setIdade(cadPetUpdate.getIdade());
            cadPetRepositorio.save(cadPetUpdate);
            return cadPetUpdate;

        }else{
            throw new ResourceNotFoundException("Registro não encontrado com id : "+ cadPet.getId());
        }

    }

    @Override
    public List<CadPet> getAllProdutos() {
        return this.cadPetRepositorio.findAll();
    }

    @Override
    public CadPet getById(long id) {
        Optional<CadPet> produtoDb=this.cadPetRepositorio.findById(id);
        if(produtoDb.isPresent())
            return produtoDb.get();
        else{
            throw new ResourceNotFoundException("Registro não encontrado com id : "+id);
        }
    }

    @Override
    public void deleteProduto(long id) {
        Optional<CadPet> produtoDb=this.cadPetRepositorio.findById(id);

        if(produtoDb.isPresent()){
           this.cadPetRepositorio.delete( produtoDb.get());
        }else
            throw new ResourceNotFoundException("Registro não encontrado com id : "+id);


    }
}
