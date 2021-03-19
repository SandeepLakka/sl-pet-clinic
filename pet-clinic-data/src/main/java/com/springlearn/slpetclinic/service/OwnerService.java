package com.springlearn.slpetclinic.service;

import com.springlearn.slpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
