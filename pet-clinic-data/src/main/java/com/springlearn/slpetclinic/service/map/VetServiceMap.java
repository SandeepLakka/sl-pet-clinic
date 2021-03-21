package com.springlearn.slpetclinic.service.map;

import com.springlearn.slpetclinic.model.Vet;
import com.springlearn.slpetclinic.service.SpecialityService;
import com.springlearn.slpetclinic.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if(!object.getSpecialities().isEmpty()){
            object.getSpecialities().forEach(speciality -> {
                if(Objects.isNull(speciality.getId())){
                    specialityService.save(speciality);
                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }
}
