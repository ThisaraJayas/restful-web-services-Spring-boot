package com.occsuss.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {//ensure we can play with data and perform operations -DAO

    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static {
        users.add(new User(++userCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount,"Jin", LocalDate.now().minusYears(20)));
    }
    public List<User> findAll(){
            return users;
    }
    public User findOne(int id){ //functional programming
        Predicate<? super User> predicate= user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id){ //functional programming
        Predicate<? super User> predicate= user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
