package core.rybina.http.controller;

import core.rybina.database.entity.Role;
import core.rybina.dto.UserReadDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model, @ModelAttribute UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);

        return "greeting/hello";
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public ModelAndView bye(ModelAndView modelAndView, @SessionAttribute("user") UserReadDto userReadDto) {
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }
//
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public ModelAndView hello2(ModelAndView modelAndView, HttpServletRequest httpServletRequest, @RequestParam("age") Integer age, @RequestHeader("accept") String accept, @CookieValue("password") String password) {
////        String age = httpServletRequest.getParameter("age");
////        httpServletRequest.getHeader("accept");
////        httpServletRequest.getCookies();
//        modelAndView.setViewName("greeting/hello");
//
//        return modelAndView;
//    }
}
