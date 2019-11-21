package me.study.spring_boot_get_start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class H2Runner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //java 8 문법 close를 자동으로 해줌.
//        try(Connection connection = dataSource.getConnection()) {
//            connection.getMetaData().getURL();
//            connection.getMetaData().getUserName();
//
//            Statement statement = connection.createStatement();
//            String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
//            statement.executeUpdate(sql);
//        }
        Connection connection = dataSource.getConnection();
        connection.getMetaData().getURL();
        connection.getMetaData().getUserName();

        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
        statement.executeUpdate(sql);

        connection.close();

        jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'donghul')");
    }
}
