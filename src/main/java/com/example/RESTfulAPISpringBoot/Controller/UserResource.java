package com.example.RESTfulAPISpringBoot.Controller;
import com.example.RESTfulAPISpringBoot.Service.UserDaoService;
import  com.example.RESTfulAPISpringBoot.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @PostMapping("/users")
    public User save(@RequestBody User user){
        return userDaoService.save(user);
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable int id){
        return userDaoService.findOne(id);
    }
}
