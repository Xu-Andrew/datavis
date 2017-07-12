package com.andrewxu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chrysaora on 6/26/17.
 */
@Controller
public class GraphController {

    @RequestMapping(value = "/graph", method = RequestMethod.GET)
    public String graph() {

        return "graph";
    }
}
