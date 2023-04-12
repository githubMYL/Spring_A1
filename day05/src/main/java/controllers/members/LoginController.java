package controllers.members;

import lombok.RequiredArgsConstructor;
import models.member.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/member/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginValidator validator;
    private final LoginService service;

    @GetMapping
    public String login(Model model) {

        Login login = new Login();
        model.addAttribute("login", login);

        return "member/login";
    }

    @PostMapping
    public String loginPs(@Valid Login login, Errors errors) {

        validator.validate(login, errors);

        if (errors.hasErrors()) {
            return "member/login";
        }

        // 로그인 처리
        service.login(login);

        return "redirect:/"; // 로그인 성공시 -> 메인페이지로 이동
    }
}