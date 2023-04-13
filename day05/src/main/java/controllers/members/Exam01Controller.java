package controllers.members;

import models.member.Member;
import models.member.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Exam01Controller {

    @Autowired
    private MemberDao memberDao;

    @GetMapping("/member2/{id}")
    public String memberInfo(@PathVariable(name="id", required = false) String userId, Model model) {
        System.out.printf("userId = %s%n", userId);

        Member member = memberDao.get(userId);
        System.out.println(member);
        model.addAttribute("member2", member);

        return "member/info2";
    }
}