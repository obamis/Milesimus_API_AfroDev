package com.rest.Mileapi.service;


import com.rest.Mileapi.model.Cad;
import com.rest.Mileapi.model.repository.CadRepository;
import com.rest.Mileapi.service.implementations.CadServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CadServiceTest {

    CadService service;
    @MockBean
    CadRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new CadServiceImplementation(repository);
    }

    @Test
    @DisplayName("Deve salvar um cad")
    public void saveCadTest(){
        Cad cad = Cad.builder().nomeDono("Bruno Felipe").nomePet("Pandora").porte("grande").raca("Husky").build();

        Mockito.when(repository.save(cad)).thenReturn(
                 Cad.builder()
                .id(1l).nomePet("Pandora")
                .porte("grande").raca("Husky")
                .nomeDono("Bruno Felipe").build());


        Cad savedCad = service.save(cad);


        assertThat(savedCad.getId()).isNotNull();
        assertThat(savedCad.getNomeDono()).isEqualTo("Bruno Felipe");
        assertThat(savedCad.getNomePet()).isEqualTo("Pandora");
        assertThat(savedCad.getPorte()).isEqualTo("grande");
        assertThat(savedCad.getRaca()).isEqualTo("Husky");


    }

    private Cad createValidCad(){
        return Cad.builder().nomePet("Pandora").porte("Grande").raca("Husky").nomeDono("Francisco").build();
    }

    @Test
    @DisplayName("Deve obter um cad pelo id")
    public void getByIdTest(){

        Long id=1l;
        Cad cad = createValidCad();
        cad.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(cad));

        Optional<Cad> encontrado = service.getById(id);
        assertThat(encontrado.isPresent()).isTrue();
        assertThat(encontrado.get().getId()).isEqualTo(id);
        assertThat(encontrado.get().getRaca()).isEqualTo(cad.getRaca());
        assertThat(encontrado.get().getNomePet()).isEqualTo(cad.getNomePet());
        assertThat(encontrado.get().getNomeDono()).isEqualTo(cad.getNomeDono());
        assertThat(encontrado.get().getPorte()).isEqualTo(cad.getPorte());
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar obter um cad pelo id, visto que não existe")
    public void InexistentegetByIdTest(){

        Long id=1l;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        Optional<Cad> encontrado = service.getById(id);
        assertThat(encontrado.isPresent()).isFalse();

    }

    @Test
    @DisplayName("Deleta um cad")
    public void deleteTest(){

        Cad cad = Cad.builder().id(1l).build(); //não preciso popular tudo, apenas o Id
        service.delete(cad);

        Mockito.verify(repository,Mockito.times(1)).delete(cad);

    }

    @Test
    @DisplayName("Gera erro ao tentar deletar um cad Inexistente")
    public void InexistentedeleteTest(){

        Cad cad = new Cad();//não preciso popular nada

       // org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, ()-> service.delete(cad));


        Mockito.verify(repository,Mockito.never()).delete(cad);

    }
    @Test
    @DisplayName("atualizar um cad ")
    public void atualizaTest(){
        long id = 1l;
        Cad updatingCad = Cad.builder().id(id).build();//não preciso popular nada,apenas id

        // org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, ()-> service.save(updatingCad));
        Cad updatedCad = createValidCad();
        updatedCad.setId(id);
        Mockito.when(repository.save(updatingCad)).thenReturn(updatedCad);

        Cad cad = service.update(updatingCad);

        assertThat(cad.getId()).isEqualTo(updatedCad.getId());
        assertThat(cad.getPorte()).isEqualTo(updatedCad.getPorte());
        assertThat(cad.getNomeDono()).isEqualTo(updatedCad.getNomeDono());
        assertThat(cad.getNomePet()).isEqualTo(updatedCad.getNomePet());
        assertThat(cad.getRaca()).isEqualTo(updatedCad.getRaca());
    }


    @Test
    @DisplayName("Gera erro ao tentar atualizar um cad Inexistente")
    public void InexistenteatualizaTest(){

        Cad cad = new Cad();//não preciso popular nada

        // org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, ()-> service.save(cad));


        Mockito.verify(repository,Mockito.never()).save(cad);

    }

}
