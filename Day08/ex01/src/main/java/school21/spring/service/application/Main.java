package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("db.properties")) {

            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

            UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
            System.out.println(usersRepository.findAll());

            usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
            System.out.println(usersRepository.findAll());

        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}
