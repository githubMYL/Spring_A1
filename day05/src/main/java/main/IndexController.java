package main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {

        String[] addCss = { "board/form", "board/fileupload" };
        List<String> addScript = Arrays.asList("board/form", "board/fileupload");

        model.addAttribute("addCss", addCss);
        model.addAttribute("addScript", addScript);

        model.addAttribute("pageTitle", "메인페이지!😭");

        return "main/index";
    }
}