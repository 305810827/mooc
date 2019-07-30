package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.Section;
import cn.gzcc.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/content")
public class LinkController {

    @Autowired
    private SectionRepository sectionRepository;

    @RequestMapping
    public ModelAndView content(int id,String username){
        ModelAndView mv = new ModelAndView();
        Section section = sectionRepository.getOne(id);
        mv.addObject("section",section);
        mv.addObject("username",username);
        mv.setViewName("linkcontent.btl");
        return mv;
    }
}
