package com.springlearn.slpetclinic.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;

}
