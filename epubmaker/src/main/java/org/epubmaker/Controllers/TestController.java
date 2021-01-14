package org.epubmaker.Controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String handlePing(){
        return "pong";
    }

}
