package cn.gzcc.demo.repository;

import cn.gzcc.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Jie on 2018/3/16.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

//    @Query(value = "INSERT  ")
//    void register(User user);

//    @Query(value = "SELECT password FROM user WHERE username = ?1",nativeQuery = true)
//    public String findPasswordByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE firstname like %?1% or lastname like %?1% or username like %?1% or role_role_id = ?1 or email like %?1%",nativeQuery = true)
    public List<User> nativeQuery(String name);

    @Query(value = "SELECT * FROM `user`,role WHERE `user`.role_role_id = role.role_id AND `user`.username = ?1",nativeQuery = true)
    public User getUserFromDatabase(String username);

}


