package com.springlearn.slpetclinic.bootstrap;

import com.springlearn.slpetclinic.model.Owner;
import com.springlearn.slpetclinic.model.Pet;
import com.springlearn.slpetclinic.model.PetType;
import com.springlearn.slpetclinic.model.Vet;
import com.springlearn.slpetclinic.service.OwnerService;
import com.springlearn.slpetclinic.service.PetService;
import com.springlearn.slpetclinic.service.PetTypeService;
import com.springlearn.slpetclinic.service.VetService;
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

    //@Autowired : Not needed as Spring implicitly takes if the class has single constructor
    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    @Override
    public void run(String... args) throws Exception {

        //TODO: Have builder implementation of these types
        createPetTypes();

        createPets();

        createOwners();

        createVets();

    }

    private void createPets() {
        Map<String, PetType> petTypeMap = petTypeService.findAll().stream().collect(
                Collectors.toMap(o -> o.getName(), Function.identity()));
        Pet dog1 = new Pet();
        dog1.setPetType(petTypeMap.get("Dog"));
        dog1.setBirthDate(LocalDate.now());
        dog1.setOwner(ownerService.findByLastName("Meghanath"));

        petService.save(dog1);

        Pet dog2 = new Pet();
        dog2.setPetType(petTypeMap.get("Dog"));
        dog2.setBirthDate(LocalDate.now().minusMonths(1));
        dog2.setOwner(ownerService.findByLastName("Meghanath"));

        petService.save(dog2);

        Pet cat1 = new Pet();
        cat1.setPetType(petTypeMap.get("Cat"));
        cat1.setBirthDate(LocalDate.now().minusWeeks(2));
        cat1.setOwner(ownerService.findByLastName("Tanush"));

        petService.save(cat1);

        Pet cat2 = new Pet();
        cat2.setPetType(petTypeMap.get("Cat"));
        cat2.setBirthDate(LocalDate.now().minusWeeks(1));
        cat2.setOwner(ownerService.findByLastName("Tanush"));

        petService.save(cat2);

        log.info("Loaded pets... {}",petService.findAll());

    }

    private void createVets() {
        Vet vet1 = new Vet();
        vet1.setFirstName("Chinnodu");
        vet1.setLastName("Chalaki");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Peddhodu");
        vet2.setLastName("Penki");

        vetService.save(vet2);

        log.info("Loaded vets... {}",vetService.findAll());
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

        log.info("Loaded Owners... {}",ownerService.findAll());
    }

    private void createPetTypes() {
        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);

        log.info("Loaded PetTypes... {}", petTypeService.findAll());
    }

}
