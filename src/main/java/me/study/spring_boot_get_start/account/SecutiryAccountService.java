package me.study.spring_boot_get_start.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class SecutiryAccountService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityAccountRepository securityAccountRepository;

    public SecurityAccount createAccount(String username, String password) {
        SecurityAccount securityAccount = new SecurityAccount();
        securityAccount.setUsername(username);
        securityAccount.setPassword(passwordEncoder.encode(password));
        return securityAccountRepository.save(securityAccount);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityAccount> byUserName = securityAccountRepository.findByUserName(username);
        SecurityAccount securityAccount = byUserName.orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(securityAccount.getUsername(), securityAccount.getPassword(), authorites()); // UserDetails 를 구현한 기본 구현체를 Spring Security에서 제공해준다.
    }

    private Collection<? extends GrantedAuthority> authorites() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
