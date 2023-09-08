package com.kmhai.cititzenV.Payload.Request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CitizenRequest {
    
    @NotNull
    private String identityCode;

    @NotNull
    private String fullName;

    @NotNull
    private LocalDate dob;

    @NotNull
    private String gender;

    @NotNull
    private String religion;

    @NotNull
    private String educationLevel;

    @NotNull
    private String jobTitle;

    @NotNull
    private AddressRequest temporaryAddress;
    
    @NotNull
    private AddressRequest permanentAddress;

    @NotNull
    private String hometownCode;
}
