package com.occsuss.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService userDaoService){
        this.service=userDaoService;
    }

    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id){
        User user =service.findOne(id);
        if (user==null){
            throw new UserNotFoundException("ID : "+id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = service.save(user);

        //user/4 => users/{id} = users  getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                //replace {id} with id of getUser
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(null).build(); //add response entity as Created
        //because we create user so it give 200 ok to 201 created
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }
}
