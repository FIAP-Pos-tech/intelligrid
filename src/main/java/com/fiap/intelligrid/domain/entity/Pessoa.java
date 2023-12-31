package com.fiap.intelligrid.domain.entity;

import com.fiap.intelligrid.controller.request.PessoaRequest;
import com.fiap.intelligrid.domain.entity.enums.Genero;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String parentesco;

    private Boolean ehAdmin;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "pessoa_endereco",
        joinColumns = @JoinColumn(name = "pessoa_id"),
        inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    private List<Endereco> enderecos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pessoa(PessoaRequest pessoaRequest) {
        this.ehAdmin = false;
        this.nome = pessoaRequest.getNome();
        this.dataNascimento = pessoaRequest.getDataNascimento();
        this.genero = pessoaRequest.getGenero();
        this.email = pessoaRequest.getEmail();
        this.parentesco = pessoaRequest.getParentesco();
    }
}