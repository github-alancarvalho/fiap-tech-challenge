package br.com.fiap.techchallenge.fiapfood.core.domain.dto;

import br.com.fiap.techchallenge.fiapfood.core.domain.entities.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoORM {

    private Long id;

    private String nome;

    private String descricao;

    private CategoriaORM categoria;

    private Double preco;

    public ProdutoORM() {
    }

    public ProdutoORM(Long id, String nome, String descricao, CategoriaORM categoria, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
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

    public CategoriaORM getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaORM categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}