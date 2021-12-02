package com.ecp.talk.controller;

import com.ecp.talk.config.APIResponse;
import com.ecp.talk.model.service.QuizRepository;
import com.ecp.talk.model.table.QuizTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;


    @PostMapping("/set")
    public Object setAns(QuizTable quiz) {
        APIResponse res = new APIResponse();
        try {
            quizRepository.save(quiz);
            res.setStatus(1);
            res.setMessage("success!");
        } catch (Exception e) {
            res.setStatus(0);
            res.setMessage("error: " + e.toString());
        }

        return res;
    }

    @GetMapping("/list")
    public Object listQuiz() {
        APIResponse res = new APIResponse();
        try {
            List<QuizTable> lstQuiz = quizRepository.findAll();
            res.setStatus(1);
            res.setMessage("list");
            res.setData(lstQuiz);
        } catch (Exception e) {
            res.setStatus(0);
            res.setMessage("error: " + e.toString());
        }

        return res;
    }

    @PostMapping("/match")
    public Object matchAns(@RequestParam String userName, @RequestParam String otherUserName) {
        APIResponse res = new APIResponse();
        try {
//            List<QuizTable> lstMatch = quizRepository.findMatchBetweenUserName(userName, otherUserName);

            List<QuizTable> data1 = quizRepository.findByUserName(userName);
            List<QuizTable> data2 = quizRepository.findByUserName(otherUserName);

            Integer percentMatch = 0;
            if (data1.size() == 5 && data2.size() == 5) {
                for (int i = 0; i < 5; i++) {
                    if(data1.get(i).getQuizId() == data2.get(i).getQuizId()){
                        if(data1.get(i).getAns() == data2.get(i).getAns()){
                            percentMatch = percentMatch + 20;
                        }
                    }
                }
            }

            res.setStatus(1);
            res.setMessage("match");
            res.setData(percentMatch);
        }catch (Exception e) {
            res.setStatus(0);
            res.setMessage("error: " + e.toString());
        }

        return res;
    }
}
