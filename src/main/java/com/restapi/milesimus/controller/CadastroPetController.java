package com.restapi.milesimus.controller;

import com.restapi.milesimus.model.CadPet;
import com.restapi.milesimus.service.CadPetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "API REST PestHouse")
@CrossOrigin(origins = "*")
public class CadastroPetController {

    @Autowired
    private CadPetService cadPetService;

    @ApiOperation(value = "Retorna lista de Pets cadastrados e seu tipo de estadia")
    @GetMapping("/pet")
    public ResponseEntity<List<CadPet>> getAllProdutos(){
        return ResponseEntity.ok().body(cadPetService.getAllProdutos());
    }

    @ApiOperation(value = "Retorna Pet cadastrados ")
    @GetMapping("/pet/{id}")
    public ResponseEntity<CadPet> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(cadPetService.getById(id));
    }


    @ApiOperation(value = "Adiciona pets a lista")
    @PostMapping("/pet")
    public ResponseEntity<CadPet>createProduto(@RequestBody CadPet cadPet){
       return  ResponseEntity.ok().body(this.cadPetService.createProduto(cadPet));
    }

        @ApiOperation(value = "Atualiza determinado pet com base no id")
    @PutMapping("/pet{id}")
    public ResponseEntity<CadPet> updateProduto(@PathVariable long id, @RequestBody CadPet cadPet){

        cadPet.setId(id);
        return  ResponseEntity.ok().body(this.cadPetService.updateProduto(cadPet));

    }
    @ApiOperation(value = "Deleta pet com base no id")
    @DeleteMapping("/pet{id}")
    public HttpStatus deleteProduct(@PathVariable long id){
        this.cadPetService.deleteProduto(id);
        return HttpStatus.OK    ;
    }
}
