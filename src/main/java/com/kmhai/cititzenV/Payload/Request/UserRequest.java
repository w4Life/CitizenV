package com.kmhai.cititzenV.Payload.Request;

import lombok.Data;

@Data
public class UserRequest {

    private String username;

    private String password;

    private String role;
}
