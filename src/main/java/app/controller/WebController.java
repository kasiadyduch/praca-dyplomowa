package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String getIndex() {
        return "index.html";
    }

    @GetMapping(value = "/private")
    public String privateArea() {
        return "private";
    }
}
