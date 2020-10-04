package com.example.RESTfulAPISpringBoot.Controller;
import com.example.RESTfulAPISpringBoot.Exception.NotFoundException;
import com.example.RESTfulAPISpringBoot.Service.UserDaoService;
import  com.example.RESTfulAPISpringBoot.domain.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @PostMapping("/users")
    public ResponseEntity<Object> save(@Valid @RequestBody User user){
        User createdUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return userDaoService.findAll();
    }

    @DeleteMapping("/users/{id}")
    public User delete(@PathVariable int id){
        User user = userDaoService.deleteById(id);
        if(user == null){
            throw new NotFoundException("id-"+ id);
        }
        return user;
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getById(@PathVariable int id){
        User user = userDaoService.findOne(id);
        if(user == null){
            throw new NotFoundException("id-"+ id);
        }
        return EntityModel.of(user,
                linkTo(methodOn(UserResource.class).getById(id)).withSelfRel(),
                linkTo(methodOn(UserResource.class).getAll()).withRel("users"));
    }
}
