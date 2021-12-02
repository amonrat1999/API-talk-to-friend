package com.ecp.talk.model.service;

import com.ecp.talk.model.table.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepository extends JpaRepository <UserTable, Integer> {

    public UserTable findByUserName(String userName);

    public UserTable findByUserNameAndPassword(String userName, String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE users " +
            "SET first_name = :firstName, " +
            "last_name = :lastName, " +
            "email = :email, " +
            "tel = :tel, " +
            "birth_day = :birthDay, " +
            "gender = :gender " +
            "WHERE user_name = :userName")
    public Integer updateByUserName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("tel") String tel,
            @RequestParam("birthDay") String birthDay,
            @RequestParam("gender") String gender,
            @RequestParam("userName") String userName
    );



}
