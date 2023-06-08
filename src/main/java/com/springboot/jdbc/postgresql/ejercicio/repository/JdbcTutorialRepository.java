package com.springboot.jdbc.postgresql.ejercicio.repository;

import com.springboot.jdbc.postgresql.ejercicio.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTutorialRepository implements TutorialRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Tutorial tutorial) {
        return jdbcTemplate.update("INSERT INTO tutorials (title, description, published) VALUES(?,?,?)",
                new Object[] { tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished() });
    }

    @Override
    public int update(Tutorial tutorial) {
        return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, published=? WHERE id=?",
                new Object[] { tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished(), tutorial.getId() });
    }

    @Override
    public Tutorial findById(Long id) {
        try {
            Tutorial tutorial = jdbcTemplate.queryForObject("select * from tutorials where id =?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id);
            return tutorial;
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from tutorials where id=?", id);
    }

    @Override
    public List<Tutorial> findAll() {
        return jdbcTemplate.query("select * from tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
    }

    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return jdbcTemplate.query("select * from tutorials where published=?", BeanPropertyRowMapper.newInstance(Tutorial.class), published);
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        String q = "select * from tutorials where title ilike '%" + title +"%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Tutorial.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("delete from tutorials");
    }
}
