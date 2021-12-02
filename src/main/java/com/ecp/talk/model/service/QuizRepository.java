package com.ecp.talk.model.service;

import com.ecp.talk.model.table.QuizTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizTable, Integer> {

    public List<QuizTable> findByUserName(String userName);

    @Query(value = "SELECT * FROM quiz WHERE user_name = :userName OR user_name = :otherUserName", nativeQuery = true)
    public List<QuizTable> findMatchBetweenUserName(
            @RequestParam("userName") String userName,
            @RequestParam("otherUserName") String otherUserName
    );


}
