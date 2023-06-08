package com.springboot.jdbc.postgresql.ejercicio.repository;

import com.springboot.jdbc.postgresql.ejercicio.model.Tutorial;

import java.util.List;

public interface TutorialRepository {
    int save(Tutorial book);
    int update(Tutorial book);
    Tutorial findById(Long id);
    int deleteById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    int deleteAll();
}
