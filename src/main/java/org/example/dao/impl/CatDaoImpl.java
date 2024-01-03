package org.example.dao.impl;

import org.example.config.DatasourceConfig;
import org.example.dao.CatDao;
import org.example.model.entity.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CatDaoImpl implements CatDao {

    private final JdbcTemplate jdbcTemplate;
    private final AnnotationConfigApplicationContext context;

    public CatDaoImpl() {
        this.context = new AnnotationConfigApplicationContext(DatasourceConfig.class);
        this.jdbcTemplate = new JdbcTemplate(context.getBean(DataSource.class));
    }

    @Override
    public Optional<Cat> getById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
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
                id));
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
        int id = this.jdbcTemplate.update(
                "INSERT INTO cats (name, breed, color, age) VALUES (?, ?, ?, ?)",
                cat.getName(), cat.getBreed(), cat.getColor(), cat.getAge());
        return getById((long) id).get();
    }

    @Override
    public Cat update(Cat cat) {
        int id = this.jdbcTemplate.update(
                "UPDATE cats SET name = ?, breed = ?, color = ?, age = ? WHERE id = ?",
                cat.getName(), cat.getBreed(), cat.getColor(), cat.getAge(), cat.getId());
        return getById((long) id).get();
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update(
                "DELETE FROM cats WHERE id = ?", id);
    }
}
