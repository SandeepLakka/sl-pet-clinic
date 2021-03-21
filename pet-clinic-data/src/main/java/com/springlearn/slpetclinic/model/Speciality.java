package com.springlearn.slpetclinic.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Speciality extends BaseEntity {
    private String description;
}
