package com.rest.Mileapi.repository;


import com.rest.Mileapi.model.Cad;
import com.rest.Mileapi.model.repository.CadRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class CadRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CadRepository cadRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro se houver o determinado cadastro que use o id informado")
    public void returnTruewhenExists(){

        String nomeDono = "Brenda Carolina";
        Cad cad = Cad.builder().porte("grande").raca("Husky")
                .nomePet("Pandora").nomeDono(nomeDono)
                .build();
        entityManager.persist(cad);
        boolean exists = cadRepository.existsByNomeDono(nomeDono);

        assertThat(exists).isTrue();
    }

}
