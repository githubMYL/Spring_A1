package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
//    @GetMapping("/hello")
//    /** @RequestParam 은 매핑을 해주어서 다른 이름이 다르더라도 찾아준다 */
//    /** required = false 면 name 값이 null 값으로 설정 되면서 페이지 이동을 시켜준다 */
//    public String hello(@RequestParam(name="name", required = false) String nm, boolean agree, Model model) {
//        System.out.printf("name=%s, agree=%s%n", nm, agree);
//        model.addAttribute("message", "안녕하세요.😎");
//
//        /** String, ModelAndView 로 반환가능 */
//        return "hello";
//    }

    @GetMapping("/hello")
    public ModelAndView hello(String name, String agree) {
        /** String 으로 대부분 사용하지만 ModelAndView 로 한번에 찾는게 좋다 */
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "안녕하세요.😎");
        mv.addObject("name", name);

        mv.setViewName("hello");
        return mv;
    }
}