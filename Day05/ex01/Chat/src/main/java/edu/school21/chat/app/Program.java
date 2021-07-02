package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;

public class Program {
    public static void CreateTables(HikariDataSource ds) throws SQLException, IOException {
        String readed = new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql")));
        PreparedStatement pst ;
        Connection con = ds.getConnection();
        pst = con.prepareStatement(readed);
        pst.executeUpdate();
    }

    public static void FillTheBase(HikariDataSource ds) throws SQLException, IOException {
        String readed = new String(Files.readAllBytes(Paths.get("src/main/resources/data.sql")));
        PreparedStatement pst ;
        Connection con = ds.getConnection();
        pst = con.prepareStatement(readed);
        pst.executeUpdate();
    }

    public static HikariDataSource getds() throws SQLException, IOException {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.addDataSourceProperty("databaseName", "ex01");
        config.addDataSourceProperty("serverName", "127.0.0.1");
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }

    public static void main (String[] args) throws SQLException, IOException {
        Scanner             input = new Scanner(System.in);
        Long                messageID = 0L;
        HikariDataSource    ds = getds();

//        CreateTables(ds);
//        FillTheBase(ds);
        while (true)
        {
            messageID = input.nextLong();
            if (messageID == 42)
                break;
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
            System.out.println(messagesRepository.findById(messageID));
        }
    }
}
