package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.repository.IposteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosteoService implements IposteoService {

    private IposteoRepository iposteoRepository;

    @Autowired
    public PosteoService(IposteoRepository iposteorepository){
        this.iposteoRepository = iposteorepository;
    };

    @Override
    public List<Posteo> obtenerPosts() {
        return iposteoRepository.findAll();
    }

    @Override
    public Posteo obtenerPostPorId(Long id) {
        return iposteoRepository.findById(id);
    }

    @Override
    public void guardarPost(Posteo post) {
        iposteoRepository.save(post);
    }
}
