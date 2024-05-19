package cn.bugstack.xfg.dev.tech.trigger.http;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin("*")
@RequestMapping("/api/user/")
public class HiGatewayController {

    /**
     * curl http://127.0.0.1:8092/api/user/hi
     */
    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String hi() {
        return "hello gateway，provider 02！";
    }

}
