package cn.gzcc.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2018/4/28.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @RequestMapping
    public  String difference()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().toString().equals("[ROLE_STUDENT]")){
            return "redirect:/courseList/";
        }
        if (auth.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            return "redirect:/user/";
        }
        else{
            return "redirect:/course/";
        }
    }
}
