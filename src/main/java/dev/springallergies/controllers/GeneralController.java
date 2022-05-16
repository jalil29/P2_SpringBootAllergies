package dev.springallergies.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Controller
public class GeneralController implements ErrorController {

    /**
     * Requires ErrorController implementation to function or application fails to launch
     **/
    @RequestMapping("/error")
    @ResponseBody
    private String ErrorResponse()
    {
        return "<h1>Page not found</h1> <img src=\"https://http.cat/404\" alt=\"Error 404 cat\"> ";
    }

    /**
     * Homepage for health check
     **/
    @GetMapping("/")
    @ResponseBody
    private String Home()
    {
        return "<h1>Potluck Backend</h1>";
    }
}
