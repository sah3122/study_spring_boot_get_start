package me.study.spring_boot_get_start.sample;

import org.springframework.hateoas.RepresentationModel;

public class Hello extends RepresentationModel {
    private String prefix;

    private String name;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
