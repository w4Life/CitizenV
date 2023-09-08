package com.kmhai.cititzenV.Payload.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {
    
    @NotNull
    private String location;

    @NotNull
    private String hamletCode;
}
