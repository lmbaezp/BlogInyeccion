package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Posteo;

import java.util.List;

public interface IposteoService {
    List<Posteo> obtenerPosts();
    Posteo obtenerPostPorId(Long id);
    void guardarPost(Posteo post);
}
