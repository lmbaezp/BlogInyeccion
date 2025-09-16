package com.sistemaBlog.blog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_autor;
    @NotNull(message = "El nombre de la persona no puede ser nulo")
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @NotNull(message = "El correo electrónico de la persona no puede ser nulo")
    @Column(name = "email", nullable = false)
    private String email;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Posteo> posts = new ArrayList<>();

    public Autor() {
    }

    public Autor(String nombre, String email, List<Posteo> posts) {
        this.nombre = nombre;
        this.email = email;
        this.posts = posts;
    }

    public Long getId_autor() {
        return id_autor;
    }

    public void setId_autor(Long id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Posteo> getPosts() {
        return posts;
    }

    public void setPosts(List<Posteo> posts) {
        this.posts = posts;
    }
}
