package me.study.spring_boot_get_start;

import me.study.spring_boot_get_start.account.SecurityAccount;
import me.study.spring_boot_get_start.account.SecutiryAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SecurityAccountRunner implements ApplicationRunner {

    @Autowired
    SecutiryAccountService secutiryAccountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SecurityAccount dongchul = secutiryAccountService.createAccount("dongchul", "1234");
        System.out.println(dongchul);
    }
}
