package com.fiap.techchallenge.entity;

import lombok.Getter;

@Getter
public enum RoleEnum {
    DONO(1, "DONO"),
    CLIENTE(2, "CLIENTE");

    private final Integer id;
    private final String nome;

    RoleEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}


