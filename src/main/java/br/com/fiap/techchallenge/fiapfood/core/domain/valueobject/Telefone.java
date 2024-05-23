package br.com.fiap.techchallenge.fiapfood.core.domain.valueobject;

public class Telefone {

    private final String telefone;

    public Telefone(String numeroCompleto) {
        this.telefone = numeroCompleto;
    }

    public Telefone(Integer codigoDeArea, Integer numero) {
        this.telefone = String.valueOf(codigoDeArea) + String.valueOf(numero);
    }

    public String getTelefone() {
        return this.telefone;
    }
}

