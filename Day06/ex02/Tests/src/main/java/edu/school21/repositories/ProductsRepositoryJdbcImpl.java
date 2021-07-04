package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Connection connection;

    ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        connection = dataSource.getConnection();
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT id, name_, price FROM product", (rs, rowNum) -> new Product(rs.getLong("id"), rs.getString("name_"), rs.getDouble("price")));
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Product product;
        Statement statement = connection.createStatement();

        ResultSet query = statement.executeQuery("SELECT * FROM product WHERE id =" + id + ";");
        if (query.next()) {
            product = new Product(query.getLong("id"), query.getString("name_"), query.getDouble("price"));
            connection.close();
            return Optional.of(product);
        }
        connection.close();
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update(
                "UPDATE product SET name_ = " + '\'' + product.getName() + '\'' + " , price = " +
                        product.getPrice() + " WHERE id = " + product.getId() + ";"
        );
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update(
                "INSERT INTO product (id, name_, price) VALUES(" + product.getId() +
                        ", " + '\'' + product.getName() + '\'' + ", " + product.getPrice() + ");"
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM product where id = ?", id);
    }
}
