package com.kmhai.cititzenV.Payload;

import java.time.LocalDate;


import lombok.Data;

@Data
public class CitizenDTO {
    
    private String identityCode;

    private String fullName;

    private LocalDate dob;

    private String gender;

    private String religion;

    private String educationLevel;

    private String jobTitle;

    private String homeTown;

    private String permanent;

    private String temporary;

    
}
