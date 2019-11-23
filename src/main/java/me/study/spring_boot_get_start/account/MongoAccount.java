package me.study.spring_boot_get_start.account;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.lang.annotation.Documented;

@Document(collation = "accounts")
public class MongoAccount {

    @Id
    private String id;

    private String username;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
