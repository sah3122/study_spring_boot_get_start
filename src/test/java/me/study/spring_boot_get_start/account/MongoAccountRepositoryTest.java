package me.study.spring_boot_get_start.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoAccountRepositoryTest {

    @Autowired
    MongoAccountRepository mongoAccountRepository;

    @Test
    public void findByEmail() throws Exception {
        //given
        MongoAccount mongoAccount = new MongoAccount();
        mongoAccount.setUsername("dongchul");
        mongoAccount.setEmail("sorkehdjcf@gmail.com");

        mongoAccountRepository.save(mongoAccount);
        //when
        Optional<MongoAccount> byId = mongoAccountRepository.findById(mongoAccount.getId());
        assertNotNull(byId);

        Optional<MongoAccount> byEmail = mongoAccountRepository.findByEmail(mongoAccount.getEmail());
        assertNotNull(byEmail);


        //then
    }

}