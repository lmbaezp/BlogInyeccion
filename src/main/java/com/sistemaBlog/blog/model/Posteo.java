package com.sistemaBlog.blog.model;

public class Posteo {
    private Long id;
    private String titulo;
    private String autor;

    public Posteo() {
    }

    public Posteo(String titulo, String autor) {
        setTitulo(titulo);
        setAutor(autor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        } else {
            this.id = id;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título del post no puede estar vacío");
        } else {
            this.titulo = titulo;
        }
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("El autor del post no puede estar vacío");
        } else {
            this.autor = autor;
        }
    }
}
