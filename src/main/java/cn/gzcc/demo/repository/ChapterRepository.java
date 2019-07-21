package cn.gzcc.demo.repository;

import cn.gzcc.demo.model.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by Jie on 2018/3/16.
 */
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
    @Query(value = "select * from chapter where chapter.course_id = ?1",nativeQuery = true)
    public List<Chapter> nativeQuery(int course_id);

    @Query(value = "DELETE FROM chapter WHERE section.course_id = ?1",nativeQuery = true)
    public List<Chapter> deletes(int course_id);

    @Query(value = "DELETE id FROM chapter WHERE id = ?1",nativeQuery = true)
    public List<Chapter> delete(int id);
}


