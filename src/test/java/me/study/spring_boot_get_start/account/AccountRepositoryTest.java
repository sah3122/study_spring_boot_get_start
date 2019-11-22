package me.study.spring_boot_get_start.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest // 슬라이싱 테스트 DataSource, JdbcTemplate, Repository 등을 주입 받는다. 인메모리 데이터 베이스가 반드시 필요함
public class AccountRepositoryTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws Exception {
        try(Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        }

        Account account = new Account();
        account.setUsername("dongchul");
        account.setPassword("pass");

        Account newAccount = accountRepository.save(account);

        assertNotNull(newAccount);

        Optional<Account> existingAccount = accountRepository.findByUserName(newAccount.getUsername());
        assertNotNull(existingAccount);

        Optional<Account> notExistingAccount = accountRepository.findByUserName("dong2");
        assertNotNull(notExistingAccount);
    }
}