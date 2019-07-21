package cn.gzcc.demo.Controller;


import cn.gzcc.demo.model.entity.Section;
import cn.gzcc.demo.model.entity.Video;
import cn.gzcc.demo.repository.SectionRepository;
import cn.gzcc.demo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


    @Controller
    @RequestMapping("/video")
    public class VideoController {

        @Autowired
        private VideoRepository videoRepository;
        @Autowired
        private SectionRepository sectionRepository;

        @RequestMapping("api")
        @ResponseBody
        public List<Video> listApi() {
            List<Video> video = videoRepository.findAll();
            return video;
        }

        @RequestMapping
        public ModelAndView list(int id){
            ModelAndView mv = new ModelAndView();
            Section section = sectionRepository.getOne(id);
            mv.addObject("section",section);
            mv.setViewName("video.btl");
            return mv;
        }

//        @RequestMapping
//        public ModelAndView list(){
//            ModelAndView mv = new ModelAndView();
//            List<Video> videos = videoRepository.findAll();
//            mv.addObject("users",videos);
//            mv.setViewName("video.btl");
//            return mv;
//        }

       @RequestMapping("/upload")
        public String upload(){
            return "/video-upload.btl";
       }
    }