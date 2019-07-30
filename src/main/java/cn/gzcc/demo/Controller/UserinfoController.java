package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.User;
import cn.gzcc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by guoyaowen on 2018/4/26.
 */
@Controller
@RequestMapping("/userInfo")
public class UserinfoController {

    @Autowired
    private UserRepository userRepository;

//    @RequestMapping
//    public String Content(){
//        return "userinfo.btl";
//    }



//    @RequestMapping("/save")
//    @ResponseBody
//    public Userinfo save(Userinfo userinfo){
////        userinfoRepository.save(userinfo);
//        userinfoRepository.save(userinfo);
//        return userinfo;
//    }

  //  @PostMapping("/save")
  //  @ResponseBody
//    public User save(int id,String nick_name,String birth_day,String sex,String address,String mobile){
////        userinfoRepository.save(userinfo);
//        User user = userRepository.getOne(id);
//        user.setUsername(nick_name);
//        user.setBirthday(birth_day);
//        user.setSex(sex);
//        user.setAddress(address);
//        user.setPhone(mobile);
//        userRepository.save(user);
//        return user;
//    }

    @RequestMapping
    public ModelAndView content(String username){
        ModelAndView mv = new ModelAndView();
        User user = userRepository.getUserFromDatabase(username);
        mv.addObject("user",user);
        mv.setViewName("userinfo.btl");
        return mv;
    }

}
