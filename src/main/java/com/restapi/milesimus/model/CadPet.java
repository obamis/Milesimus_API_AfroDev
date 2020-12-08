package com.restapi.milesimus.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table (name = "TB_CrecheHotel")
public class CadPet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( name = "nome_pet")
    private String nome;

    @Column( name = "raca")
    private String raca;

    @Column( name = "porte")
    private String porte;

    @Column( name = "idade")
    private int idade;

    @Column( name = "periodo_permanencia")
    private String tipo_estadia;

    @Column( name = "preco")
    private BigDecimal preco_estadia;

    @Column(name = "passeador")
    private boolean passeador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTipo_estadia() {
        return tipo_estadia;
    }

    public void setTipo_estadia(String tipo_estadia) {
        this.tipo_estadia = tipo_estadia;
    }

    public BigDecimal getPreco_estadia() {
        return preco_estadia;
    }

    public void setPreco_estadia(BigDecimal preco_estadia) {
        this.preco_estadia = preco_estadia;
    }

    public CadPet setId() {
        //gerado automáticamente, por isso aqui retorna null
        return null;
    }
}
