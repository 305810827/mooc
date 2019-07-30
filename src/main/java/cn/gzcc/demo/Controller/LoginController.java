package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.User;
import cn.gzcc.demo.repository.RoleRepository;
import cn.gzcc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by lenovo on 2018/4/28.
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        return "login.btl";
    }

//    @GetMapping("/loginError")
//    public String loginError() {
//        System.out.println("404.html");
//        return "pages-404.html";
//    }
//
//    @GetMapping(value = "/logout")
//    public ResultJson logout(HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultJson.failure(ResultCode.UNAUTHORIZED);
//        }
//        authService.logout(token);
//        return ResultJson.ok();
//    }

    @PostMapping(value = "/accountIsExist")
    @ResponseBody
    public boolean accountIsExist(@RequestParam String username) {

        System.out.println(userRepository.findByUsername(username)+"userRepository");
        try {
            if(userRepository.findByUsername(username) != null)
                return false;
            else
                return true;
        }catch (NullPointerException e){
            return true;
        }
    }

    @PostMapping(value = "/sign")
    @ResponseBody
    public String sign(@RequestParam("username")String username,
                       @RequestParam("name")String name,
                       @RequestParam("password")String password,
                       @RequestParam("roleId")int roleId,
                       @RequestParam("email")String email) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(username,encoder.encode(password),name,name,email,true,new Date(),roleRepository.findByRoleId(roleId));
        try {
            userRepository.save(user);
            return "success";
        }catch (Exception e){
            throw new RuntimeException("注册失败："+e.getMessage());
        }
    }
}
