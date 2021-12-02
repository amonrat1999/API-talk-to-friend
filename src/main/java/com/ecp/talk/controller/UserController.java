package com.ecp.talk.controller;

import com.ecp.talk.config.APIResponse;
import com.ecp.talk.model.service.UserRepository;
import com.ecp.talk.model.table.UserTable;
import net.bytebuddy.asm.Advice;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Object register(UserTable user){
        APIResponse res = new APIResponse();
        try{
            UserTable userDetail = userRepository.findByUserName(user.getUserName());
            if (userDetail == null){
                userRepository.save(user);
                res.setStatus(1);
                res.setMessage("success");
                res.setData(user);
            }else {
                res.setStatus(2);
                res.setMessage("repeat");
            }
        }catch (Exception e){
            res.setStatus(0);
            res.setMessage("error: "+e.toString());
        }
        return res;
    }

    @PostMapping("/login")
    public Object login(@RequestParam String userName, @RequestParam String password){
        APIResponse res = new APIResponse();
        try{
            UserTable checkUser = userRepository.findByUserName(userName);
            if (checkUser == null){
                res.setStatus(3);
                res.setMessage("username not found");
            }else {
                UserTable checkPass = userRepository.findByUserNameAndPassword(userName, password);
                if (checkPass == null){
                    res.setStatus(2);
                    res.setMessage("password wrong");
                }else {
                    res.setStatus(1);
                    res.setMessage("login success");
                    res.setData(checkUser);
                }
            }
        }catch (Exception e){
            res.setStatus(0);
            res.setMessage("error: "+e.toString());
        }
        return res;
    }

    @PutMapping("/update")
    public Object update(UserTable user){
        APIResponse res = new APIResponse();
        try {
            Integer status = userRepository.updateByUserName(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getTel(),
                    user.getBirthDay(),
                    user.getGender(),
                    user.getUserName()
            );
            if (status > 0) {
                res.setStatus(1);
                res.setMessage("update success");
                res.setData(user);
            }
        }catch (Exception e){
            res.setStatus(0);
            res.setMessage("error: "+e.toString());
        }
        return res;
    }

    @PostMapping("/detail")
    public Object detail(@RequestParam String userName){
        APIResponse res = new APIResponse();
        try {
            UserTable detail = userRepository.findByUserName(userName);
            res.setStatus(1);
            res.setMessage("detail");
            res.setData(detail);
        }catch (Exception e){
            res.setStatus(0);
            res.setMessage("error: "+e.toString());
        }
        return res;
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam Integer userId){
        APIResponse res = new APIResponse();
        try {
            userRepository.deleteById(userId);
            res.setStatus(1);
            res.setMessage("delete success!");
        }catch (Exception e){
            res.setStatus(0);
            res.setMessage("error: "+e.toString());
        }
        return res;
    }




}
