package com.sistemaBlog.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comentario;
    @NotNull(message = "El contenido del comentario no puede ser nulo")
    @Column(name = "texto", nullable = false)
    private String texto;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "id_post", nullable = false)
    @JsonBackReference
    private Posteo id_post;


    public Comentario() {
    }

    public Comentario(String texto, LocalDateTime createdAt, Posteo id_post) {
        this.texto = texto;
        this.createdAt = createdAt;
        this.id_post = id_post;
    }

    public Long getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Long id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Posteo getId_post() {
        return id_post;
    }

    public void setId_post(Posteo id_post) {
        this.id_post = id_post;
    }
}
