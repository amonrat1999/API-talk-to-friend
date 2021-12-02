package com.ecp.talk.model.table;


import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Data
@Entity(name = "quiz")
public class QuizTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    String userName;

    @Column
    int quizId;

    @Column
    int ans;

}
