package com.fiap.techchallenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"usuarios"})
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<UsuarioEntity> usuarios = new ArrayList<>();

    public void addUsuario(UsuarioEntity usuario) {
        usuarios.add(usuario);
        usuario.setRole(this);
    }

    public void removeUsuario(UsuarioEntity usuario) {
        usuarios.remove(usuario);
        usuario.setRole(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity)) return false;
        return id != null && id.equals(((RoleEntity) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
