package me.study.spring_boot_get_start.account;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Neo4jAccountRepository extends Neo4jRepository<Neo4jAccount, Long> {
}
