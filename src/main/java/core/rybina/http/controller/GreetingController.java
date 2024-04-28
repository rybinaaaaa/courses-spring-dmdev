package core.rybina.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/v1")
public class GreetingController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest httpServletRequest, @RequestParam("age") Integer age, @RequestHeader("accept") String accept, @CookieValue("password") String password) {
//        String age = httpServletRequest.getParameter("age");
//        httpServletRequest.getHeader("accept");
        httpServletRequest.getCookies();
        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public ModelAndView bye(ModelAndView modelAndView) {
//        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }
}
