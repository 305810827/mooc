package cn.gzcc.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String roleName;
    private String auth;

    public int getRoleId() {
        return roleId;
    }

    @JsonBackReference
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    @JsonBackReference
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAuth() {
        return auth;
    }

    @JsonBackReference
    public void setAuth(String auth) {
        this.auth = auth;
    }


//    @OneToMany(mappedBy = "role")
//    private List<User> users =new ArrayList<>();
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    @JsonBackReference
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
