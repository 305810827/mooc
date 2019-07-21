package cn.gzcc.demo.repository;


import cn.gzcc.demo.model.entity.student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StuRspository extends JpaRepository<student,Integer> {
}
