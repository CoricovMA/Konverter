package org.konverter.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String handlePing(){
        return "pong";
    }
}
