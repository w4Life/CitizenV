package com.kmhai.cititzenV.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.UserDTO;
import com.kmhai.cititzenV.Payload.Request.UserRequest;

@Service
public interface UserService {

    List<UserDTO> getAll();

    String createUser(UserRequest user, String divisionCode);

    String createUserByA1(UserRequest u);
}
