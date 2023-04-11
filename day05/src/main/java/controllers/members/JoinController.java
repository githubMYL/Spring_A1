package controllers.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/join")  /** 공통적인 부분을 묶어준다 */
public class JoinController {
    @GetMapping    /** /member/join */
    public String join(Model model) {

//        model.addAttribute("title", "회원가입");
//        model.addAttribute("content", "회원가입내용");

        Join join = new Join();
        model.addAttribute("join", join);

        return "member/join";
    }

    @PostMapping   /** /member/join */
    public String joinPs(Join join, Model model) {
        System.out.println(join);
//        System.out.println(model);
        System.out.println("POST로 유입!😁");
        return "member/join";
//        return "redirect:/member/login";

    }
}
