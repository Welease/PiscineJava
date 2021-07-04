package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new LinkedList<>();
    private final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2, "phone", 300);
    private ProductsRepositoryJdbcImpl productsRepositoryJdbc;
    private DataSource dataSource;

    @BeforeEach
    void init() throws SQLException {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        dataSource = databaseBuilder.addScript("schema.sql").addScript("data.sql").build();
        productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void findByIdTest() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepositoryJdbc.findById(EXPECTED_FIND_BY_ID_PRODUCT.getId()).get());
    }

    @Test
    void findAllTest() {
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1, "computer", 400));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2, "phone", 300));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3, "tv", 200));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(4, "chair", 100));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(5, "table", 500));

        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepositoryJdbc.findAll());
    }

    @Test
    void deleteTest() {
        EXPECTED_FIND_ALL_PRODUCTS.clear();
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1, "computer", 400));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2, "phone", 300));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3, "tv", 200));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(4, "chair", 100));

        productsRepositoryJdbc.delete(5L);

        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepositoryJdbc.findAll());
    }

    @Test
    void updateTest() throws SQLException {
        Product product = new Product(1, "comp", 344);

        productsRepositoryJdbc.update(product);

        Assertions.assertEquals(product, productsRepositoryJdbc.findById(product.getId()).get());
    }

    @Test
    void saveTest() throws SQLException {
        Product product = new Product(10, "prod", 344);

        productsRepositoryJdbc.save(product);

        Assertions.assertEquals(product, productsRepositoryJdbc.findById(product.getId()).get());
    }
}
