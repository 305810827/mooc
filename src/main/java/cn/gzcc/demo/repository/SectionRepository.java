package cn.gzcc.demo.repository;

import cn.gzcc.demo.model.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by Jie on 2018/3/16.
 */
public interface SectionRepository extends JpaRepository<Section,Integer> {
    @Query(value = "select * from section where section.course_id = ?1",nativeQuery = true)
    public List<Section> nativeQuery(int course_id);

    @Query(value = "select * from section where id limit ?1,?2",nativeQuery = true)
    public List<Section> nativeQuery1(int pageNum, int pageSize);

    @Modifying
    @Query(value= "DELETE FROM section WHERE chapter_id=?1",nativeQuery=true)
    public List<Section> nativeQuery4(int chapter_id);

    @Query(value = "DELETE FROM section WHERE section.course_id = ?1",nativeQuery = true)
    public List<Section> deletess(int course_id);

    @Query(value = "select * from section where chapter_id = ?1",nativeQuery = true)
    public List<Section> nativeQuery6(int course_id);

    @Query(value = "select * from section where id = ?1",nativeQuery = true)
    public List<Section> nativeQuery3(int id);


}


