package cn.gzcc.demo.Controller;

import cn.gzcc.demo.model.entity.User;
import cn.gzcc.demo.repository.RoleRepository;
import cn.gzcc.demo.repository.UserRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Jie on 2018/3/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    @ResponseBody
    public Map<String, Object> userList() {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userRepository.findAll();
        map.put("users", users);
        return map;
    }

//    @RequestMapping
//    public ModelAndView userList() {
//        ModelAndView mv = new ModelAndView();
//        List<User> users = userRepository.findAll();
//        System.out.println(users.get(0).getUsername()+"users");
//        mv.addObject("users",users);
//        mv.setViewName("user.btl");
//        return mv;
//    }

    @RequestMapping("/api")
    @ResponseBody
    public List<User> listApi() {
        List<User> users = userRepository.findAll();

        return users;
    }

//    @RequestMapping("/courseManager")
//    public ModelAndView courseList(){
//        ModelAndView mv = new ModelAndView();
//        List<User> users = userRepository.findAll();
//        mv.addObject("users",users);
//        mv.setViewName("courseManager.btl");
//        return mv;
//    }

//    @RequestMapping("/form")
//    public ModelAndView form(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("Upload.btl");
//        return mv;
//    }

    @Autowired
    private RoleRepository roleRepository;
    @RequestMapping("/save")
    @ResponseBody
    public User save(@RequestParam("username")String username,
                     @RequestParam("name")String name,
                     @RequestParam("password")String password,
                     @RequestParam("roleId")int roleId,
                     @RequestParam("email")String email) {
        System.out.println(username+name+password+roleId+email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(username,encoder.encode(password),name,name,email,true,new Date(),roleRepository.findByRoleId(roleId));
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public List<User> delete(int id) {
        userRepository.deleteById(id);
        List<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping("/deletes")
    @ResponseBody
    public String deletes(int[] args) {
        for (int i=0;i<args.length;i++){
            userRepository.deleteById(args[i]);
        }
        return "redirect:/user";
    }


//    @RequestMapping("/search")
//    public ModelAndView searchList(String name2){
//        ModelAndView mv = new ModelAndView();
//        List<User> users = userRepository.nativeQuery(name2);
//        mv.addObject("user",users);
//        mv.setViewName("Upload.btl");
//        return mv;
//    }
//
//    @RequestMapping(value = {"/check"},method ={RequestMethod.POST})
//    @ResponseBody
//    @HttpConstraint
//    public String Check(HttpServletRequest request) throws IOException{
//        String name =request.getParameter("name");
//        User un = userRepository.findByUsername(name);
//        if (un == null)
//        {
//            return "enable";
//        }
//        else {
//            return "exist";
//        }
//
//    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<User> search(String name) {
        List<User> users = new ArrayList<>();
        System.out.println(name);
        if(name == null || name == ""){
            users = userRepository.findAll();
        }else{
            users = userRepository.nativeQuery(name);
        }
        return users;
    }

    @RequestMapping("excelUpload")
    @ResponseBody
    public int excelUpload(MultipartFile file) {
        try {
            int i, j;
            List<Map<String, String>> students = new ArrayList<>();
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int firstRow = sheet.getFirstRowNum();//姓名那行的索引
            int lastRow = sheet.getLastRowNum();//最后一行的索引
            for (i = 1; i < lastRow + 1; i++) {
                Map map = new HashMap();
                XSSFRow row = sheet.getRow(i);//第i行
                int rowFirstCell = row.getFirstCellNum();//第i行的第一个单元格的索引
                int rowLastCell = row.getLastCellNum();//第i行的最后单元格的索引
                for (j = rowFirstCell; j < rowLastCell; j++) {

                    XSSFCell cell2 = sheet.getRow(firstRow + 1).getCell(j);
                    if (cell2.getCellTypeEnum() == CellType.NUMERIC) {
                        cell2.setCellType(CellType.STRING);
                    }
                    String key = cell2.getStringCellValue();

                    XSSFCell cell = row.getCell(j);
                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                        cell.setCellType(CellType.STRING);
                    }
                    String val = cell.getStringCellValue();
                    if (i == firstRow + 1) {
                        break;
                    } else {
                        map.put(key, val);
                    }
                }
                if (i != firstRow + 1) {
                    students.add(map);
                }
            }
            XSSFRow indexRow = sheet.getRow(firstRow + 1);//从这里开始写进数据库
            int celllength = indexRow.getLastCellNum();
            String a[] = new String[celllength];
            for (i = 0; i < celllength; i++) {
                a[i] = indexRow.getCell(i).getStringCellValue();
            }
            List<User> list1 = new ArrayList<>();
            User excelUploadStudent1 = new User();
            excelUploadStudent1.setPassword(a[0]);
            excelUploadStudent1.setLastname(a[1]);
            excelUploadStudent1.setId(0);
            list1.add(excelUploadStudent1);
            userRepository.save(excelUploadStudent1);

            int listStudets = students.size();//student长度

            for (i = 0; i < listStudets; i++) {
                User excelUploadStudent = new User();
                excelUploadStudent.setPassword(students.get(i).get(a[0]));
                excelUploadStudent.setLastname(students.get(i).get(a[1]));
                excelUploadStudent.setId(0);
                list1.add(excelUploadStudent);
                userRepository.save(excelUploadStudent);
            }
            String dsd = "dsdsdsd";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
