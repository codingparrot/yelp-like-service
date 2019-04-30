package interview.parrot.questions.yelplike.controller;

import interview.parrot.questions.yelplike.dto.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author interviewparrot created on 19-Mar-2019
 */
@RestController
public class PingHandler {

    @RequestMapping("/ping")
    public String ping(){
        return ResponseStatus.OK.name();
    }
}
