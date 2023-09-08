package com.kmhai.cititzenV.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "citizens")
@Data
public class Citizen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String identityCode;

    private String fullName;

    private LocalDate dob;

    private String gender;

    @ManyToOne
    @JoinColumn(name = "home_town_code", referencedColumnName = "code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Hamlet hometown;

    @ManyToOne
    @JoinColumn(name = "permanent_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Address permanent;

    @ManyToOne
    @JoinColumn(name = "temporary_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Address temporary;

    private String religion;

    private String educationLevel;

    private String jobTitle;
}
