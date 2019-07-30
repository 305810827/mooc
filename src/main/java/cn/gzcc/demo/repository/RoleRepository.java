package cn.gzcc.demo.repository;

import cn.gzcc.demo.model.entity.Role;
import cn.gzcc.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Jie on 2018/3/16.
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query(value = "SELECT * FROM role WHERE role_id =?1",nativeQuery = true)
    public Role findByRoleId(int roleId);

}


