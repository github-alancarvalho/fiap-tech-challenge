package br.com.fiap.techchallenge.fiapfood.core.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDto {

    private Long id;
    private String nome;
    private String descricao;

    public CategoriaDto(Long id, String nome, String descricao) {
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