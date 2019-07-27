package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.User;
import cn.gzcc.demo.repository.UserRepository;
import cn.gzcc.demo.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    @ResponseBody
    public Integer loginSuccess(HttpServletRequest request)
    {
        String token = request.getHeader(tokenHeader);
        JwtTokenUtil jwttokenUtil = new JwtTokenUtil();
        String username = jwttokenUtil.getUsernameFromToken(token);
        User UserInfo = userRepository.getUserFromDatabase(username);
//        if (UserInfo.getRole().getRoleId() == 1){
//            System.out.println("UserInfo.getRole().getRoleId()"+UserInfo.getRole().getRoleId());
//            return "user.btl";
//        }
//        else if (UserInfo.getRole().getRoleId() == 2) {
//            return "redirect:/loginSuccess/courseList";
//        }
//        else if(UserInfo.getRole().getRoleId() == 3){
//            return "redirect:/loginSuccess/course";
//        }
        return UserInfo.getRole().getRoleId();
    }

    @RequestMapping(value = "/user")
    public String User(){
        return "user.btl";
    }

    @RequestMapping(value = "/courseList")
    public String CourseList(){
        return "courseList.btl";
    }

    @RequestMapping(value = "/course")
    public String Course(){
        return "course.btl";
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
