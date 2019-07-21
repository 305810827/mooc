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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by Jie on 2018/3/5.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SearchRepository searchRepository;


    @RequestMapping
    public ModelAndView Page(@RequestParam(defaultValue = "1")int pageNum) {
        ModelAndView mv = new ModelAndView();
        int pageSize = 2;
        //在这里就要将PageBean中的数据创建好，然后将该对象传回去，
        //先要从数据库中获取所以课程的数量有多少，获得totalRecord
        List<Course> courses1 = courseRepository.findAll();
        List<Chapter> chapters = chapterRepository.findAll();
        List<Section> sections = sectionRepository.findAll();
        int totalRecord = courses1.size();
        //有了三个初始数据，就能够创建pageBean对象了
        PageBean pageBean = new PageBean(pageNum,pageSize,totalRecord);
        //获取PageBean对象中的startIndex
        int startIndex  = pageBean.getStartIndex();
        //有了startIndex和pageSize，就可以拿到每页的数据了
        List<Course> courses = courseRepository.nativeQuery(startIndex,pageSize);
        mv.addObject("pageBean", pageBean);
        mv.addObject("courses", courses);
        mv.addObject("chapters", chapters);
        mv.addObject("sections", sections);
        mv.setViewName("courseManager.btl");
        return mv;
    }

//    @RequestMapping("/index")
//    public ModelAndView course(int id,@RequestParam(defaultValue = "1")int pageNum){
//        ModelAndView mv = new ModelAndView();
//        Course courses = courseRepository.getOne(id);
//        List<Chapter> chapters = chapterRepository.nativeQuery(id);
//        List<Section> sections = sectionRepository.nativeQuery(id);
//        mv.addObject("courses",courses);
//        mv.addObject("chapters",chapters);
//        mv.addObject("sections",sections);
//        mv.setViewName("courseManager.btl");
//        return mv;
//    }

    @RequestMapping("/save")
    public ModelAndView save(Course course, @RequestParam(defaultValue = "1")int pageNum){
        ModelAndView mv = new ModelAndView();
        courseRepository.save(course);
        List<Course> courses = courseRepository.findAll();
        List<Chapter> chapters = chapterRepository.findAll();
        List<Section> sections = sectionRepository.findAll();
        int pageSize = 2;
        int totalRecord = courses.size();
        //有了三个初始数据，就能够创建pageBean对象了
        PageBean pageBean = new PageBean(pageNum,pageSize,totalRecord);
        int startIndex  = pageBean.getStartIndex();
        //有了startIndex和pageSize，就可以拿到每页的数据了
        courses = courseRepository.nativeQuery(startIndex,pageSize);
        mv.addObject("pageBean", pageBean);
        mv.addObject("courses",courses);
        mv.addObject("chapters", chapters);
        mv.addObject("sections", sections);
        mv.setViewName("courseManager.btl");
        return mv;
    }

    @RequestMapping("/search")
    public ModelAndView search(String key,@RequestParam(defaultValue = "1")int pageNum){
        ModelAndView mv = new ModelAndView();
        List<Course> courses = searchRepository.findSearch(key);
        List<Chapter> chapters = chapterRepository.findAll();
        List<Section> sections = sectionRepository.findAll();
        int pageSize = 2;
        int totalRecord = courses.size();
        //有了三个初始数据，就能够创建pageBean对象了
        PageBean pageBean = new PageBean(pageNum,pageSize,totalRecord);
        int startIndex  = pageBean.getStartIndex();
        //有了startIndex和pageSize，就可以拿到每页的数据了
        courses = courseRepository.nativeQuery(startIndex,pageSize);
        mv.addObject("pageBean", pageBean);
        mv.addObject("courses",courses);
        mv.addObject("chapters", chapters);
        mv.addObject("sections", sections);
        mv.setViewName("courseManager.btl");
        return mv;
    }

    @RequestMapping("/delete")
    public String delete(int course_id){

        int a;
        List<Chapter> chapters = chapterRepository.nativeQuery(course_id);
        for(Chapter chapter : chapters)
        {
        List<Section> section1 = sectionRepository.nativeQuery6(chapter.getId());
        for(Section section : section1)
        {
            sectionRepository.deleteById(section.getId());
        }
        chapterRepository.deleteById(chapter.getId());
        }
        courseRepository.deleteById(course_id);

        return "redirect:/course";
    }

    @GetMapping("/delete1")
    public String delete1(int chapter_id){
        List<Section> section1 = sectionRepository.nativeQuery6(chapter_id);
        for(Section section : section1)
        {
            sectionRepository.deleteById(section.getId());
        }
        chapterRepository.deleteById(chapter_id);
        return "redirect:/course";
    }

    @RequestMapping("/delete2")
    public String delete2(int id){
        sectionRepository.deleteById(id);
        return "redirect:/course";
    }
}
