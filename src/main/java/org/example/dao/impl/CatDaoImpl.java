package org.example.dao.impl;

import org.example.config.DatasourceConfig;
import org.example.dao.CatDao;
import org.example.model.entity.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CatDaoImpl implements CatDao {

    //    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private AnnotationConfigApplicationContext context;

    public CatDaoImpl() {
        this.context = new AnnotationConfigApplicationContext(DatasourceConfig.class);
        this.jdbcTemplate = new JdbcTemplate(context.getBean(DataSource.class));
    }

//    private AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//    private final Optional<Connection> connection;
//    ConfigReader configReader = context.getBean(ConfigReader.class);
//
//    public CatDaoImpl() {
//
////        this.connection = context.getBean(DbConnection.class).getConnection();
////        this.connection = new DbConnection().getConnection();
////        this.connection = new DbConnection().getConnection();
//    }

    @Override
    public Optional<Cat> getById(Long id) {
        Cat cat = jdbcTemplate.queryForObject(
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
        Optional<Cat> c = Optional.of(cat);
        return c;
//        if (connection.isPresent()) {
//            String sql = "SELECT id, name, breed, color, age FROM cats WHERE id = ?";
//            try (var preparedStatement = connection.get().prepareStatement(sql)) {
//                preparedStatement.setLong(1, id);
//                var resultSet = preparedStatement.executeQuery();
//                var list = getAllFromResultSet(resultSet);
//                return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
//            } catch (SQLException ex) {
//                throw new SqlExecuteException(ex);
//            }
//        }
//        throw new ConnectionException();
    }

    @Override
    public List<Cat> getAll() {
        return List.of();
//        if (connection.isPresent()) {
//            String sql = "SELECT id, name, breed, color, age FROM cats";
//            try {
//                var resultSet = connection.get().createStatement().executeQuery(sql);
//                return getAllFromResultSet(resultSet);
//            } catch (SQLException ex) {
//                throw new SqlExecuteException(ex);
//            }
//        }
//        throw new ConnectionException();
    }

    @Override
    public Cat create(Cat cat) {
        return null;
//        if (cat == null) {
//            throw new IllegalArgumentException(CAT_IS_NULL_ERROR);
//        }
//        if (connection.isPresent()) {
//            String sql = "INSERT INTO cats (name, breed, color, age) VALUES (?, ?, ?, ?)";
//            try (PreparedStatement ps = connection.get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//                ps.setString(1, cat.getName());
//                ps.setString(2, cat.getBreed());
//                ps.setString(3, cat.getColor());
//                ps.setInt(4, cat.getAge());
//                ps.executeUpdate();
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    return getById(rs.getLong(1)).get();
//                }
//            } catch (SQLException ex) {
//                throw new EntityNotFoundException();
//            }
//        }
//        throw new ConnectionException();
    }

    @Override
    public Cat update(Cat cat) {
        return null;
//        if (cat == null) {
//            throw new IllegalArgumentException(CAT_IS_NULL_ERROR);
//        }
//        if (connection.isPresent()) {
//            String sql = "UPDATE cats SET name = ?, breed = ?, color = ?, age = ? WHERE id = ?";
//            Optional<Cat> exists = getById(cat.getId());
//            if (exists.isPresent()) {
//                try (var preparedStatement = connection.get().prepareStatement(sql)) {
//                    preparedStatement.setString(1, cat.getName());
//                    preparedStatement.setString(2, cat.getBreed());
//                    preparedStatement.setString(3, cat.getColor());
//                    preparedStatement.setInt(4, cat.getAge());
//                    preparedStatement.setLong(5, cat.getId());
//                    preparedStatement.executeUpdate();
//                } catch (SQLException ex) {
//                    throw new SqlExecuteException(ex);
//                }
//            } else {
//                throw new CatNotFoundException(cat.getId());
//            }
//            return getById(cat.getId()).orElseThrow(
//                    () -> new CatNotFoundException(cat.getId())
//            );
//        }
//        throw new ConnectionException();
    }

    @Override
    public void delete(Long id) {
//        if (connection.isPresent()) {
//            String sql = "DELETE FROM cats WHERE id = ?";
//            try (var preparedStatement = connection.get().prepareStatement(sql)) {
//                preparedStatement.setLong(1, id);
//                preparedStatement.executeUpdate();
//            } catch (SQLException ex) {
//                throw new SqlExecuteException(ex);
//            }
//        } else {
//            throw new ConnectionException();
//        }
    }

    private List<Cat> getAllFromResultSet(ResultSet resultSet) {
        try {
            List<Cat> catList = new ArrayList<>();
            while (resultSet.next()) {
                var cat = Cat
                        .builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .breed(resultSet.getString("breed"))
                        .color(resultSet.getString("color"))
                        .age(resultSet.getInt("age"))
                        .build();
                catList.add(cat);
            }
            return catList;
        } catch (SQLException ignored) {
        }
        return List.of();
    }
}
