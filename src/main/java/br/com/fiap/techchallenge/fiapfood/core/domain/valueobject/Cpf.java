package br.com.fiap.techchallenge.fiapfood.core.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Cpf implements Serializable {

    @NotNull
    @Column(name = "cpf", nullable = false)
    private final String cpf;

    public Cpf(String cpf) {
        // Remove caracteres especiais e espaços em branco
        this.cpf = cpf.replaceAll("[^0-9]", "");
        validarCPF(this.cpf);
    }

    public String getCpfSomenteNumero() {
        return cpf;
    }

    private void validarCPF(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        // Calcula e verifica o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        if (Character.getNumericValue(cpf.charAt(9)) != digito1) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        // Calcula e verifica o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        if (Character.getNumericValue(cpf.charAt(10)) != digito2) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return Objects.equals(cpf, cpf.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return cpf;
    }

}

