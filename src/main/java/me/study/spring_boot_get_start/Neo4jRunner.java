package me.study.spring_boot_get_start;

import me.study.spring_boot_get_start.account.Neo4jAccount;
import me.study.spring_boot_get_start.account.Neo4jAccountRepository;
import me.study.spring_boot_get_start.account.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    Neo4jAccountRepository neo4jAccountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Neo4jAccount neo4jAccount = new Neo4jAccount();
        neo4jAccount.setEmail("sorkehdcjf@gmail.com");
        neo4jAccount.setUsername("dongchul");

        Role role = new Role();

        role.setName("admin");

        neo4jAccount.getRoles().add(role);

        Session session = sessionFactory.openSession();
        session.save(neo4jAccount);
        sessionFactory.close();

        neo4jAccountRepository.save(neo4jAccount);
    }
}
