package cn.gzcc.demo.repository;


import cn.gzcc.demo.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by Jie on 2018/3/16.
 */
public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query(value = "select * from course where id limit ?1,?2",nativeQuery = true)
    public List<Course> nativeQuery(int pageNum,int pageSize);

}


