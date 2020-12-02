package com.course.springboot.services.controllers.api.users;

import com.course.springboot.services.vo.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ControllerImpl for Users Services
 */
@RestController
public class UsersController implements UsersApi{

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getUsers(String name) {
        List<User> usersDTO = users;
        if(name != null && !"".equals(name)) {
            usersDTO = users.stream().filter(user -> name.equals(user.getName())).collect(Collectors.toList());
        }
        return usersDTO;
    }

    @Override
    public User getUser(int id) {
        return users.stream().filter(user -> user.getIdUser() == id).findFirst().orElse(new User());
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User userDTO) {
        User userAux = users.stream().filter(user -> user.getIdUser() == userDTO.getIdUser()).findFirst().orElse(null);
        if(userAux != null) {
            userAux.setIdUser(userDTO.getIdUser());
            userAux.setName(userDTO.getName());
            userAux.setSurname(userDTO.getSurname());
        }
    }

    @Override
    public void deleteUser(int id) {
        users.removeIf(user -> user.getIdUser() == id);
    }
}
