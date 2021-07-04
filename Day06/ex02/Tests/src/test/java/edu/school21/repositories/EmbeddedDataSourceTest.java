package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private DataSource dataSource;

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        dataSource = databaseBuilder.addScript("schema.sql").addScript("data.sql").build();
    }

    @Test
    void connectionTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }
}
