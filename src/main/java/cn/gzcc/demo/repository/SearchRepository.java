package cn.gzcc.demo.repository;


import cn.gzcc.demo.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Jie on 2018/3/16.
 */
public interface SearchRepository extends JpaRepository<Course,Integer> {

    @Query(value = "SELECT * FROM course  WHERE id =?1 or course_name like %?1% or lecturer like %?1% or difficulty like %?1% or describe1 like %?1%",nativeQuery=true)
    public List<Course> findSearch(String query);

}


