package org.example.dao.impl;

import org.example.config.DatasourceConfig;
import org.example.dao.CatDao;
import org.example.exception.EntityNotFoundException;
import org.example.model.entity.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.example.constant.Constant.CAT_IS_NULL_ERROR;

public class CatDaoImpl implements CatDao {

    private final JdbcTemplate jdbcTemplate;
    private final AnnotationConfigApplicationContext context;

    public CatDaoImpl() {
        this.context = new AnnotationConfigApplicationContext(DatasourceConfig.class);
        this.jdbcTemplate = new JdbcTemplate(context.getBean(DataSource.class));
    }

    @Override
    public Optional<Cat> getById(Long id) {
        List<Cat> cats = jdbcTemplate.query(
                "SELECT id, name, breed, color, age FROM cats WHERE id = ?",
                (resultSet, rowNum) -> {
                    return Cat
                            .builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .breed(resultSet.getString("breed"))
                            .color(resultSet.getString("color"))
                            .age(resultSet.getInt("age"))
                            .build();
                },
                id);

        if (cats.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(cats.get(0));
        }
    }

    @Override
    public List<Cat> getAll() {
        return this.jdbcTemplate.query(
                "SELECT id, name, breed, color, age FROM cats",
                (resultSet, rowNum) -> {
                    return Cat
                            .builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .breed(resultSet.getString("breed"))
                            .color(resultSet.getString("color"))
                            .age(resultSet.getInt("age"))
                            .build();
                });
    }

    @Override
    public Cat create(Cat cat) {
        if (cat == null) {
            throw new IllegalArgumentException(CAT_IS_NULL_ERROR);
        }
        String sql = "INSERT INTO cats (name, breed, color, age) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cat.getName());
            ps.setString(2, cat.getBreed());
            ps.setString(3, cat.getColor());
            ps.setInt(4, cat.getAge());
            return ps;
        }, keyHolder);
        var id = (Long) keyHolder.getKeyList().get(0).get("id");
        return getById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Cat update(Cat cat) {
        if (cat == null) {
            throw new IllegalArgumentException(CAT_IS_NULL_ERROR);
        }
        this.jdbcTemplate.update(
                "UPDATE cats SET name = ?, breed = ?, color = ?, age = ? WHERE id = ?",
                cat.getName(), cat.getBreed(), cat.getColor(), cat.getAge(), cat.getId());
        return getById(cat.getId()).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update("DELETE FROM cats WHERE id = ?", id);
    }
}
