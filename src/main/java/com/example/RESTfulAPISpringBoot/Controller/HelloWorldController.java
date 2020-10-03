package com.example.RESTfulAPISpringBoot.Controller;

import com.example.RESTfulAPISpringBoot.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    //get
    //uri - hello-world
    //method
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello world";
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")

    public String helloWorldPath(@PathVariable String name){
        return "Hello world " + name;
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world bean");
    }
}
