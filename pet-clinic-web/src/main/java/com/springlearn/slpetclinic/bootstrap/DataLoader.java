package com.springlearn.slpetclinic.bootstrap;

import com.springlearn.slpetclinic.model.*;
import com.springlearn.slpetclinic.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialityService specialityService;

    //@Autowired : Not needed as Spring implicitly takes if the class has single constructor
    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, PetService petService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialityService = specialityService;
    }


    @Override
    public void run(String... args) throws Exception {

        //TODO: Have builder implementation of these types
        int count = specialityService.findAll().size();

        //Only load when fresh run : Is nice when we have JPA impl on top of existing Map impl
        if(count == 0) {
            loadData();
        }

    }

    private void loadData() {
        createPetTypes();

        createOwners();

        createPets();

        createSpecialities();

        createVets();
    }

    private void createSpecialities() {
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        specialityService.save(dentistry);

        log.info("Loaded Specialities...");
    }

    private void createPets() {
        Map<String, PetType> petTypeMap = petTypeService.findAll().stream().collect(
                Collectors.toMap(o -> o.getName(), Function.identity()));

        Owner meghanath = ownerService.findByLastName("Meghanath");
        Owner tanush = ownerService.findByLastName("Tanush");

        Pet dog1 = new Pet();
        dog1.setPetType(petTypeMap.get("Dog"));
        dog1.setName("Snoopy");
        dog1.setBirthDate(LocalDate.now());
        dog1.setOwner(meghanath);
        meghanath.getPets().add(dog1);

        petService.save(dog1);

        Pet dog2 = new Pet();
        dog2.setPetType(petTypeMap.get("Dog"));
        dog2.setName("Jimmy");
        dog2.setBirthDate(LocalDate.now().minusMonths(1));
        dog2.setOwner(meghanath);
        meghanath.getPets().add(dog2);

        petService.save(dog2);

        Pet cat1 = new Pet();
        cat1.setPetType(petTypeMap.get("Cat"));
        cat1.setName("Kitty");
        cat1.setBirthDate(LocalDate.now().minusWeeks(2));
        cat1.setOwner(tanush);
        tanush.getPets().add(cat1);

        petService.save(cat1);

        Pet cat2 = new Pet();
        cat2.setPetType(petTypeMap.get("Cat"));
        cat2.setName("Dolly");
        cat2.setBirthDate(LocalDate.now().minusWeeks(1));
        cat2.setOwner(tanush);
        tanush.getPets().add(cat2);

        petService.save(cat2);

        log.info("Loaded Pets...");

    }

    private void createVets() {

        Map<String, Speciality> specialityMap = specialityService.findAll().stream()
                .collect(Collectors.toMap(o -> o.getDescription(), Function.identity()));

        Vet vet1 = new Vet();
        vet1.setFirstName("Chinnodu");
        vet1.setLastName("Chalaki");
        vet1.getSpecialities().add(specialityMap.get("Radiology"));

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Peddhodu");
        vet2.setLastName("Penki");
        vet2.getSpecialities().addAll(specialityMap.values());

        vetService.save(vet2);

        log.info("Loaded Vets...");
    }

    private void createOwners() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Lucky");
        owner1.setLastName("Meghanath");
        owner1.setAddress("HomeSweetHome");
        owner1.setCity("Hyderabad");
        owner1.setTelephone("9988776655");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Ricky");
        owner2.setLastName("Tanush");
        owner2.setAddress("HomeAlone3");
        owner2.setCity("Hyderabad");
        owner2.setTelephone("4433221100");

        ownerService.save(owner2);

        log.info("Loaded Owners...");
    }

    private void createPetTypes() {
        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);

        log.info("Loaded PetTypes...");
    }

}
