package br.com.fiap.techchallenge.fiapfood.core.domain.dto;

import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.domain.valueobject.Telefone;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;
@Data
@Builder
public class ClienteORM {

    private Cpf cpf;
    private String nome;
    private String email;
    private Telefone telefone;

    public ClienteORM(Cpf cpf, String nome, String email, Telefone telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteORM cliente = (ClienteORM) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

}
