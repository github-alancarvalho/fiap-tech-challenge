package br.com.fiap.techchallenge.fiapfood.core.domain.valueobject;

import java.io.Serializable;

public class Telefone implements Serializable {

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

