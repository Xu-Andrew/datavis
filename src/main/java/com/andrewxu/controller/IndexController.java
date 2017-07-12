package com.andrewxu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chrysaora on 6/17/17.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        if (name == null) {
            name = "World";
        }
        model.addAttribute("message", "Hello " + name + "!");
        return "index";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void internalServerError(HttpServletRequest request, Model model) {
        throw new NullPointerException();
    }
}
