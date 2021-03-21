package com.springlearn.slpetclinic.service.map;

import com.springlearn.slpetclinic.model.Owner;
import com.springlearn.slpetclinic.service.OwnerService;
import com.springlearn.slpetclinic.service.PetService;
import com.springlearn.slpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        Owner savedOwner = null;

        if (Objects.nonNull(owner)) {
            owner.getPets().forEach(pet -> {
                if (Objects.nonNull(pet.getPetType())) {
                    if (Objects.isNull(pet.getPetType().getId())) {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                } else {
                    throw new RuntimeException("Pet Type is mandatory");
                }

                if (Objects.isNull(pet.getId())) {
                    petService.save(pet);
                }
            });
            savedOwner = super.save(owner);
        }

        return savedOwner;
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findByLastName(String lastName) {
        return super.findAll().stream().filter(
                owner -> owner.getLastName().equals(lastName))
                .findFirst().orElse(null);
    }
}
