package br.com.fiap.techchallenge.fiapfood.adapter.driven.infra.repositories.mariadb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "categoria")
@NamedQuery(name = "findAllCategorias", query = "SELECT c FROM CategoriaORM c")
public class CategoriaORM {

    @NotNull
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "descricao", nullable = true)
    private String descricao;

    public CategoriaORM() {
    }

    public CategoriaORM(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}