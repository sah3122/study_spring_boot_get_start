package me.study.spring_boot_get_start.account;

import org.springframework.data.repository.CrudRepository;

public interface RedisAccountRepository extends CrudRepository<RedisAccount, String> {
}
