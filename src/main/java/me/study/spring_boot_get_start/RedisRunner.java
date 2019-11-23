package me.study.spring_boot_get_start;

import me.study.spring_boot_get_start.account.RedisAccount;
import me.study.spring_boot_get_start.account.RedisAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisAccountRepository redisAccountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
        values.set("dongchul", "lee");
        values.set("springboot", "2.0");

        RedisAccount redisAccount = new RedisAccount();
        redisAccount.setEmail("sorkehdcjf@gmail.com");
        redisAccount.setUsername("dongchul");

        redisAccountRepository.save(redisAccount);

        Optional<RedisAccount> findAccount = redisAccountRepository.findById(redisAccount.getId());
        System.out.println(findAccount.get().getUsername());
        System.out.println(findAccount.get().getEmail());
    }
}
