package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.User;
import cn.gzcc.demo.repository.UserRepository;
import cn.gzcc.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lenovo on 2018/4/28.
 */
@Controller
@RequestMapping(value = "/loginSuccess")
public class RoleController {

    private String tokenHeader = "Authorization";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String loginSuccess(HttpServletRequest request)
    {
        String token = request.getHeader(tokenHeader);
        TokenUtils tokenUtils = new TokenUtils();
        String username = tokenUtils.getUsernameFromToken(token);
        System.out.println("username"+username);
        User UserInfo = userRepository.getUserFromDatabase(username);
        if (UserInfo.getRole().getRoleId() == 1){
            return "redirect:/user/";
        }
        else if (UserInfo.getRole().getRoleId() == 2) {
            return "redirect:/courseList/";
        }
        else if(UserInfo.getRole().getRoleId() == 3){
            return "redirect:/course/";
        }
        return null;
    }

//    @RequestMapping
//    public ModelAndView loginSuccess(HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        TokenUtils tokenUtils = new TokenUtils();
//        String username = tokenUtils.getUsernameFromToken(token);
//        User UserInfo = userRepository.getUserFromDatabase(username);
//        ModelAndView mv = new ModelAndView();
//        if(UserInfo.getRole().getRoleId() == 1){
//
//        }else if(UserInfo.getRole().getRoleId() == 2){
//            List<User> users = userRepository.findAll();
//            mv.addObject("users", users);
//            mv.setViewName("user.btl");
//        }else if(UserInfo.getRole().getRoleId() == 3){
//
//        }
//
//
//        return mv;
//    }
}
