package com.sistemaBlog.blog.repository;

import com.sistemaBlog.blog.model.Posteo;

import java.util.List;

public interface IposteoRepository {
    List<Posteo> findAll();
    Posteo findById(Long id);
    void save(Posteo post);
}
