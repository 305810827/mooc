package cn.gzcc.demo.Controller;


import cn.gzcc.demo.model.entity.Chapter;
import cn.gzcc.demo.model.entity.Course;
import cn.gzcc.demo.model.entity.Section;
import cn.gzcc.demo.repository.ChapterRepository;
import cn.gzcc.demo.repository.CourseRepository;
import cn.gzcc.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/post")
public class PostController {
//    @Autowired
//    private LessonRespository lessonRespository;
//    @Autowired
//    private WeeksRespository weeksRespository;

    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Value("${icoll.web.upload.root-path}")
    private String rootPath;


    @GetMapping
    public ModelAndView postForm(int id) {
        ModelAndView mv = new ModelAndView();
        List<Chapter> chapters = chapterRepository.nativeQuery(id);
        mv.addObject("chapters",chapters);
        mv.setViewName("postCourse.btl");
        return mv;
    }

//    @RequestMapping("/save1")
//    @ResponseBody
//    public String save1(Chapter chapter){
//        chapterRepository.save(chapter);
//        return "/course";
//    }

    @RequestMapping("/save1")
    public String chapter(HttpServletRequest request)throws ServletException, IOException{
        String course_id = request.getParameter("course_id");
        String chapter_name = request.getParameter("chapter_name");
        int course=0;
        try {
            course = Integer.valueOf(course_id).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Course course1= courseRepository.getOne(course);
        chapterRepository.save(new Chapter(chapter_name,course1));
        return "redirect:/course";
    }
//
//    @RequestMapping("/save2")
//    public String save2(Course course){
//        courseRepository.save(course);
//        return "redirect:/course";
//    }

//    @RequestMapping("/save3")
//    public String chapter1(HttpServletRequest request)throws ServletException, IOException{
//
//        String chapter_id = request.getParameter("chapter_id");
//        String chapter_name = request.getParameter("chapter_name");
//        String course_id = request.getParameter("course_id");
//        int course=0;
//        int chapter=0;
//        try {
//            course = Integer.valueOf(course_id).intValue();
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//        try {
//            chapter = Integer.valueOf(chapter_id).intValue();
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//        Course course1= courseRepository.getOne(course);
//        chapterRepository.save(new Chapter(chapter,chapter_name,course1));
//        return "redirect:/course";
//    }

//    @GetMapping("/ap")
//    @ResponseBody
//    public List<Lesson> listLesson(){
//        List<Lesson> lessons=lessonRespository.findAll();
//        return lessons;
//    }
    @RequestMapping("/post1")
    @ResponseBody
    public String greetingSubmit(HttpServletRequest request,boolean booleanfile)throws ServletException, IOException{
        String videolink=null;
        if(booleanfile) {
            try {
                Part partfiles = request.getPart("files");
                String fileName = partfiles.getSubmittedFileName().substring(partfiles.getSubmittedFileName().lastIndexOf("."));//获取从右边开始第一个“.”
                String lastfile = UUID.randomUUID().toString() + fileName;
                File pathone = new File( rootPath+"/vid" );
//                if (!pathone.exists()) pathone = new File( "" );
                String releativePathfile = pathone + lastfile;
                Path savefilepath = Paths.get(releativePathfile);
                Files.copy(partfiles.getInputStream(), savefilepath);
                videolink = "/uploads/vid" + lastfile;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        String course_id = request.getParameter("course.id");
        String chapter_id = request.getParameter("chapter.id");
        String section_name = request.getParameter("section_name");
        String section_content = request.getParameter("testeditormd-html-code");
        int chapter=0;
        try {
            chapter = Integer.valueOf(chapter_id).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

       Chapter chapter1= chapterRepository.getOne(chapter);
       sectionRepository.save(new Section(section_name,section_content,chapter1,videolink));
        return "/course";

    }

    @RequestMapping("/upload")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part =request.getPart("editormd-image-file");
        String fileExtensionName = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));//获取从右边开始第一个“.”
        //选取一行代码，右键Evalute Expression，可打开计算器，测试你需要用的方法。
        File path = new File( rootPath+"/ima");
        String LastName=UUID.randomUUID().toString()+fileExtensionName;
        String releativePath= path+ LastName;
        Path savePath= Paths.get(releativePath);
        Files.copy(part.getInputStream(),savePath);
        String absolutePath1="/uploads/ima"+ LastName;
//        Uploadfiles/image
        response.getWriter().print("{\"success\":1,\"message \": \"fail\",\"url\":\""+absolutePath1+"\"}");
    }

}
