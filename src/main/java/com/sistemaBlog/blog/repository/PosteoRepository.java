//package com.sistemaBlog.blog.repository;
//
//import com.sistemaBlog.blog.model.Posteo;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class PosteoRepository implements IposteoRepository {
//    private List<Posteo> posts = new ArrayList<>();
//
//    public PosteoRepository() {
//        Posteo posteo1 = new Posteo("Post 1", "Autor 1");
//        posteo1.setId_autor(1L);
//        Posteo posteo2 = new Posteo("Post 2", "Autor 2");
//        posteo2.setId_autor(2L);
//        Posteo posteo3 = new Posteo("Post 3", "Autor 3");
//        posteo3.setId_autor(3L);
//        Posteo posteo4 = new Posteo("Post 4", "Autor 4");
//        posteo4.setId_autor(4L);
//        Posteo posteo5 = new Posteo("Post 5", "Autor 5");
//        posteo5.setId_autor(5L);
//
//        posts.add(posteo1);
//        posts.add(posteo2);
//        posts.add(posteo3);
//        posts.add(posteo4);
//        posts.add(posteo5);
//    }
//
//    @Override
//    public List<Posteo> findAll() {
//        return posts;
//    }
//
//    @Override
//    public Posteo findById(Long id) {
//        for (Posteo post : posts) {
//            if(post.getId_autor().equals(id)){
//                return post;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void save(Posteo post) {
//        Long maxId = 0L;
//        for (Posteo p : posts) {
//            if (p.getId_autor() > maxId) {
//                maxId = p.getId_autor();
//            }
//        }
//        post.setId_autor(maxId + 1);
//        posts.add(post);
//    }
//}
