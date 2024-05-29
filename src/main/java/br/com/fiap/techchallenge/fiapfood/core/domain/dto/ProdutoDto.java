package br.com.fiap.techchallenge.fiapfood.core.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDto {

    private Long id;

    private String nome;

    private String descricao;

    private CategoriaDto categoria;

    private Double preco;

    public ProdutoDto() {
    }

    public ProdutoDto(Long id, String nome, String descricao, CategoriaDto categoria, Double preco) {
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

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}