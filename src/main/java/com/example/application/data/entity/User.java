package com.example.application.data.entity;

import javax.persistence.*;

//import com.example.application.data.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.Set;

import com.example.application.data.Role;

@Entity
@Table(name = "user")
public class User{// extends AbstractEntity {

    private int idnum;
    private String username;
    private String name;
    @JsonIgnore
    private String hashed_password;
  //  @Transient private Set<Role> roles;
    private String role;
    @Lob
    private String profilePictureUrl;


    public User(int id, String username, String name, String hashedPassword, String role, String profilePictureUrl) {
        this.idnum = id;
        this.username = username;
        this.name = name;
        this.hashed_password = hashedPassword;
        this.role = role;
        this.profilePictureUrl = profilePictureUrl;

//        if(role.equals("admin")){
//            roles = Collections.singleton(Role.ADMIN);
//        }
//        else{
//            roles = Collections.singleton(Role.USER);
//        }

    }

    public User() {
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getIdnum() {
        return idnum;
    }

    public void setIdnum(int id) {
        this.idnum = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedPassword() {
        return hashed_password;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashed_password = hashedPassword;
    }
//    @Transient
//    public Set<Role> getRoles() {
//        if(role.equals("admin")){
//            return Collections.singleton(Role.ADMIN);
//        }
//        else{
//            return Collections.singleton(Role.USER);
//        }
//    }
//    @Transient
//    public void setRoles(Set<Role> roles) {
//        System.out.println(""+roles.toString());
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

}
