package bmis.com.bmis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/")
    public String home() {
        return "redirect:/books/list";
    }
}
