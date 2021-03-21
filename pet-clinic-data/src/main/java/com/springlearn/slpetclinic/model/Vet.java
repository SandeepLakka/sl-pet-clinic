package com.springlearn.slpetclinic.model;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString(callSuper = true)
public class Vet extends Person {

    private Set<Speciality> specialities;

}
