package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.User;
import cn.gzcc.demo.repository.UserRepository;
import cn.gzcc.demo.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> loginSuccess(HttpServletRequest request)
    {
        String token = request.getHeader(tokenHeader);
        JwtTokenUtil jwttokenUtil = new JwtTokenUtil();
        String username = jwttokenUtil.getUsernameFromToken(token);
        User UserInfo = userRepository.getUserFromDatabase(username);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId",UserInfo.getRole().getRoleId());
        map.put("username",username);
        return map;
    }

    @RequestMapping(value = "/user")
    public ModelAndView User(String username){
        ModelAndView mv = new ModelAndView();
        mv.addObject("username",username);
        mv.setViewName("user.btl");
        return mv;
    }

//    @RequestMapping(value = "/courseList")
//    public ModelAndView CourseList(String username){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("username",username);
//        mv.setViewName("courseList.btl");
//        return mv;
//    }

    @RequestMapping(value = "/course")
    public ModelAndView Course(String username){
        ModelAndView mv = new ModelAndView();
        mv.addObject("username",username);
        mv.setViewName("course.btl");
        return mv;
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
