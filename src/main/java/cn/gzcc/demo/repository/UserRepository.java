package cn.gzcc.demo.repository;

import cn.gzcc.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Jie on 2018/3/16.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByName(String name);

    @Query(value = "SELECT * FROM user WHERE id =?1 or name like %?1% or password like %?1% or phone like %?1% or address like %?1% or birthday like %?1% or sex like %?1% or username like %?1%",nativeQuery = true)
    public List<User> nativeQuery(String name);

    @Query(value = "SELECT * FROM `user`,role WHERE `user`.role_id = role.role_id AND `user`.username = ?1",nativeQuery = true)
    public User getUserFromDatabase(String username);
    //`user`.username,`user`.`password`,`user`.role_id,`user`.enable,`user`.last_password_change,`user`.enable,role.auth
}


