package com.ecp.talk.model.table;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int userId;

    @Column
    String firstName;

    @Column
    String LastName;

    @Column
    String userName;

    @Column
    String password;

    @Column
    String email;

    @Column
    String tel;

    @Column
    String birthDay;

    // เพศ
    @Column
    String gender;

    @Column
    String image;


}
