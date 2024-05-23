package br.com.fiap.techchallenge.fiapfood.core.domain.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

public enum StatusPedido {
    EM_PREPARACAO, RECEBIDO, PRONTO, ENTREGUE
}

