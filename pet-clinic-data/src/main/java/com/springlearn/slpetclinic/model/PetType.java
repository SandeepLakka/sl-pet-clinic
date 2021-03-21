package com.springlearn.slpetclinic.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class PetType extends BaseEntity {

    private String name;
}
