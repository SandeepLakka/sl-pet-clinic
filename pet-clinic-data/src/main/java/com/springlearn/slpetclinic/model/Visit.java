package com.springlearn.slpetclinic.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString(callSuper = true)
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;

}
