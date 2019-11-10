package me.study.spring_boot_get_start;

import me.dongchul.Holoman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 *  ApplicationRunner
 *  application을 뛰웠을때 자동으로 실행
 */
public class HolomanRunner implements ApplicationRunner {

    @Autowired
    Holoman holoman;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoman);
    }
}
