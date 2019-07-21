package cn.gzcc.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String descriptin;

    @OneToMany(mappedBy = "role")
    private List<User> users =new ArrayList<>();

    public int getId() {
        return id;
    }

    @JsonBackReference
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonBackReference
    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptin() {
        return descriptin;
    }

    @JsonBackReference
    public void setDescriptin(String descriptin) {
        this.descriptin = descriptin;
    }

    public List<User> getUsers() {
        return users;
    }

    @JsonBackReference
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
