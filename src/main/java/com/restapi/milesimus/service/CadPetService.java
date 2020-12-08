package com.restapi.milesimus.service;

import com.restapi.milesimus.model.CadPet;

import java.util.List;

public interface CadPetService {

    CadPet createProduto(CadPet cadPet);

    CadPet updateProduto(CadPet cadPet);

    List<CadPet> getAllProdutos();

    CadPet getById(long id);

    void deleteProduto(long id);



     /* List<CadPet>getAllRaca();
    List<CadPet>getAllPorte();
    List<CadPet>getAllIdade();
    List<CadPet>getAllPasseador();
    List<CadPet>getAllNome();
    List<CadPet>getAllTipoHospedagem();
    List<CadPet>getByNome();
*/
}






