package me.study.spring_boot_get_start.template;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "dongchul");
        return "hello";
    }

}
