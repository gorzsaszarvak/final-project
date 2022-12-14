package hu.unideb.inf.finalproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }
}
