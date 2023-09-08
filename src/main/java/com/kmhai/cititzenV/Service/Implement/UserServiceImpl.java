package com.kmhai.cititzenV.Service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.UserDTO;
import com.kmhai.cititzenV.Payload.Request.UserRequest;
import com.kmhai.cititzenV.Repository.RoleRepo;
import com.kmhai.cititzenV.Repository.UserRepo;
import com.kmhai.cititzenV.Service.UserService;
import com.kmhai.cititzenV.Service.UtilsService;
import com.kmhai.cititzenV.entity.Role;
import com.kmhai.cititzenV.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UtilsService utils;

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepo.findAll();
        List<UserDTO> res = users.stream().map(u -> mapper.map(u, UserDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < users.size(); i++) {
            res.get(i).setRole(users.get(i).getRole().getRole());
        }

        return res;
    } 

    @Override
    public String createUser(UserRequest user, String divisionCode) {
       
        if (utils.checkValidUser(user, divisionCode) == "ok") {
            Role role = roleRepo.findByRole(user.getRole());
            User save = mapper.map(user, User.class);
            save.setRole(role);
            userRepo.save(save);
            return "ok";
        }

        return utils.checkValidUser(user, divisionCode);
    }

    @Override
    public String createUserByA1(UserRequest user) {
        if (utils.checkValidUserByA1(user) == "ok") {
            Role role = roleRepo.findByRole(user.getRole());
            User save = mapper.map(user, User.class);
            save.setRole(role);
            userRepo.save(save);
            return "ok";
        }

        return utils.checkValidUserByA1(user);
    }
}
