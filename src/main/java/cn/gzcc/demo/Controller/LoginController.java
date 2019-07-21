package cn.gzcc.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2018/4/28.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login.btl";
    }
}
