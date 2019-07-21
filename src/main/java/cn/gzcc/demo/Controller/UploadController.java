package cn.gzcc.demo.Controller;

import cn.gzcc.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class UploadController {

//    @Value("${icollege.web.uoload.root-path}")
//    private String rootPath;

    @GetMapping("/upload")
    public String form(){
        return "/upload.btl";
    }

    @Autowired
    SectionRepository sectionRepository;

    @PostMapping("/upload")
    @ResponseBody
    public String form(int id,String name,@RequestPart("files") MultipartFile[] files){
        try {
            int index = 0;
             for(MultipartFile file : files) {
                 File f = new File("/video/"  + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")) + file.getOriginalFilename());
                 file.transferTo(f);
             }
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        String videoName="/video/"+name;
//        sectionRepository.nativeQuery2(id,videoName);
        return "ss";
    }


//    @RequestMapping
//    @ResponseBody
//    public String handleFormUpload(String name, MultipartFile[] files) throws IOException{
//        return "success";
//
//    }
}
