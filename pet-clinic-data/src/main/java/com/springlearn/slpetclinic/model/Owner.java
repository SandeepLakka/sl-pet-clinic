package com.springlearn.slpetclinic.model;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString(callSuper = true)
public class Owner extends Person {

    private Set<Pet> pets;

}
