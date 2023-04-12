package controllers.members;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import models.member.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/member/join")  /** 공통적인 부분을 묶어준다 */
@RequiredArgsConstructor    /** final, @NonNull이 있는 멤버 변수 초기화 */
public class JoinController {

    @NonNull
    private final JoinValidator validator;

    private final JoinService service;

    /** @RequiredArgsConstructor 를 사용하면  이부분을 작성 생략해도 됨
    public JoinController(JoinValidator validator) {
        this.validator = validator;
    }
    */

    @GetMapping    /** /member/join */
    public String join(Model model) {

//        model.addAttribute("title", "회원가입");
//        model.addAttribute("content", "회원가입내용");

        Join join = new Join();
        model.addAttribute("join", join);

        return "member/join";
    }

    @PostMapping   /** /member/join */
    /** @valid를 사용해야 검증을 해줌 (대신 커맨드객체뒤에 Errors 가 들어와야 된다)*/
    public String joinPs(@Valid Join join, Errors errors, Model model) {
        validator.validate(join, errors);

        if (errors.hasErrors()) {
            /** 에러가 있으면 처리X -> 양식 */
            return "member/join";
        }
//        System.out.println(model);
//        System.out.println("POST로 유입!😁");
//        return "member/join";

        /** 회원 가입처리 */

        service.join(join);

        return "redirect:/member/login";

    }
}
