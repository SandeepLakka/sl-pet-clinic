package com.springlearn.slpetclinic.service.map;

import com.springlearn.slpetclinic.model.Pet;
import com.springlearn.slpetclinic.service.PetService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(), pet);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }
}
