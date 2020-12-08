package com.rest.Mileapi.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.Mileapi.dto.CadDTO;
import com.rest.Mileapi.model.Cad;
import com.rest.Mileapi.service.CadService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class CadControllerTest {

    //rota
    static String Cad_API = "/api/pets";

    @Autowired
    MockMvc mvc;

    @MockBean
    CadService service;

    @Test
    @DisplayName("Deve criar um PetCadastro com sucesso")
    public void createCadTest() throws Exception {
        CadDTO dto = createNewCad();
        Cad savedCad = Cad.builder().id(1l).nomeDono("Bruno").nomePet("Harry").porte("pequeno").raca("shitzu").build();

        BDDMockito.given(service.save(Mockito.any(Cad.class))).willReturn(savedCad);
        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Cad_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("nomeDono").value(dto.getNomeDono()))
                .andExpect(jsonPath("nomePet").value(dto.getNomePet()))
                .andExpect(jsonPath("porte").value(dto.getPorte()))
                .andExpect(jsonPath("raca").value(dto.getRaca()));

    }

    @Test
    @DisplayName("Retorna Informações sobre um pet cadastrado com base no id")
    public void getCadTest() throws Exception {

        Long id = 1l;
        Cad cad = Cad.builder().id(id).nomeDono(createNewCad().getNomeDono()).nomePet(createNewCad().getNomePet())
                .raca(createNewCad().getRaca())
                .porte(createNewCad().getPorte()).build();

        BDDMockito.given(service.getById(id)).willReturn(Optional.of(cad));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Cad_API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("nomeDono").value(createNewCad().getNomeDono()))
                .andExpect(jsonPath("nomePet").value(createNewCad().getNomePet()))
                .andExpect(jsonPath("porte").value(createNewCad().getPorte()))
                .andExpect(jsonPath("raca").value(createNewCad().getRaca()))
        ;
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar um cadastro que não foi listado")
    public void cadNotFoundTest() throws Exception {

        BDDMockito.given(service.getById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Cad_API.concat("/" + 1))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve Deletar um Cadastro")
    public void deleteCadTest() throws Exception{

        BDDMockito.given(service.getById(anyLong())).willReturn(Optional.of(Cad.builder().id(1l).build()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(Cad_API.concat("/" + 1));

        mvc
            .perform( request )
            .andExpect(status()
            .isNoContent());

    }

    @Test
    @DisplayName("Deve retornar erro se tentar deletar um Cadastro que não existe")
    public void deleteInexistenteCadTest() throws Exception{

        BDDMockito.given(service.getById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(Cad_API.concat("/" + 1));

        mvc
                .perform( request )
                .andExpect(status()
                        .isNotFound());

    }

    @Test
    @DisplayName("Deve Atualizar um cadastro")
    public  void updateCadTest() throws Exception{

        long id = 1l;
        String json = new ObjectMapper().writeValueAsString(createNewCad());

        Cad updatingCad = Cad.builder().id(1l).nomeDono("Geovana").raca("Husky").porte("grande").nomePet("Pandora").build();
        BDDMockito.given(service.getById(id)).willReturn(Optional.of(updatingCad));

        Cad updatedCad = Cad.builder().id(id).nomeDono("Bruno").nomePet("Harry").porte("pequeno").raca("shitzu").build();
        BDDMockito.given(service.update(updatingCad)).willReturn(updatedCad);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(Cad_API.concat("/" + 1))
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc
                .perform( request )
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("nomeDono").value(createNewCad().getNomeDono()))
                .andExpect(jsonPath("nomePet").value(createNewCad().getNomePet()))
                .andExpect(jsonPath("porte").value(createNewCad().getPorte()))
                .andExpect(jsonPath("raca").value(createNewCad().getRaca()))
                ;


    }

    @Test
    @DisplayName("Quando não existe o cadastro a ser atualizado")
    public  void InexistenteupdateCadTest() throws Exception{

        String json = new ObjectMapper().writeValueAsString(createNewCad());
        BDDMockito.given(service.getById(Mockito.anyLong()))
                .willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(Cad_API.concat("/" + 1))
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc
                .perform( request )
                .andExpect(status()
                        .isNotFound())
                ;
    }


    private CadDTO createNewCad() {
        return CadDTO.builder().nomeDono("Bruno").nomePet("Harry").porte("pequeno").raca("shitzu").build();


}


//    @Test
//    @DisplayName("Deve lançar erro por receber dados insuficientes")
//    public void createInvalidCadTest() throws Exception {
//
//        String json = new ObjectMapper().writeValueAsString(new CadDTO());
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .post(Cad_API)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(json);
//
//        mvc.perform(request)
//                .andExpect( status().isBadRequest() )
//                .andExpect( jsonPath("errors", hasSize(4)));
//    }
}