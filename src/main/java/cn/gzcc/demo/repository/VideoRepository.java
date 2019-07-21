package cn.gzcc.demo.repository;

import cn.gzcc.demo.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Jie on 2018/3/16.
 */
public interface VideoRepository extends JpaRepository<Video,Integer> {
    @Query(value = "select * from user where user.name = ?1",nativeQuery = true)
    public List<Video> nativeQuery(String name);


}
