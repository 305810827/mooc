package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.Chapter;
import cn.gzcc.demo.model.entity.Course;
import cn.gzcc.demo.model.entity.PageBean;
import cn.gzcc.demo.model.entity.Section;
import cn.gzcc.demo.repository.ChapterRepository;
import cn.gzcc.demo.repository.CourseRepository;
import cn.gzcc.demo.repository.SearchRepository;
import cn.gzcc.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by Jie on 2018/3/5.
 */
@Controller
@RequestMapping("/courseList")
public class CourseListController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SearchRepository searchRepository;
    @RequestMapping
    public ModelAndView Page(@RequestParam(defaultValue = "1")int pageNum,String username) {
        ModelAndView mv = new ModelAndView();
        int pageSize = 4;
        //在这里就要将PageBean中的数据创建好，然后将该对象传回去，
        //先要从数据库中获取所以课程的数量有多少，获得totalRecord
        List<Course> courses = courseRepository.findAll();
        int totalRecord = courses.size();
        //有了三个初始数据，就能够创建pageBean对象了
        PageBean pageBean = new PageBean(pageNum,pageSize,totalRecord);
        //获取PageBean对象中的startIndex
        int startIndex  = pageBean.getStartIndex();
        //有了startIndex和pageSize，就可以拿到每页的数据了
        List<Course> courses1 = courseRepository.nativeQuery(startIndex,pageSize);
        mv.addObject("pageBean", pageBean);
        mv.addObject("courses", courses1);
        mv.addObject("username",username);
        mv.setViewName("courseList.btl");
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView course(int id,@RequestParam(defaultValue = "1")int pageNum,String username){
        ModelAndView mv = new ModelAndView();
        Course courses = courseRepository.getOne(id);
        List<Chapter> chapters = chapterRepository.nativeQuery(id);
        List<Section> sections = sectionRepository.findAll();
        mv.addObject("courses",courses);
        mv.addObject("chapters",chapters);
        mv.addObject("sections",sections);
        mv.addObject("username",username);
        mv.setViewName("course.btl");
        return mv;
    }

    @RequestMapping("/search")
    public ModelAndView search(String key,@RequestParam(defaultValue = "1")int pageNum){
        ModelAndView mv = new ModelAndView();

        List<Course> courses = searchRepository.findSearch(key);

        int pageSize = 4;
        int totalRecord = courses.size();
        //有了三个初始数据，就能够创建pageBean对象了
        PageBean pageBean = new PageBean(pageNum,pageSize,totalRecord);
        mv.addObject("pageBean", pageBean);
        mv.addObject("courses",courses);
        mv.setViewName("courseList.btl");
        return mv;
    }

}
