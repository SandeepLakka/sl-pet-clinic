package com.springlearn.slpetclinic.bootstrap;

import com.springlearn.slpetclinic.model.Owner;
import com.springlearn.slpetclinic.model.Vet;
import com.springlearn.slpetclinic.service.OwnerService;
import com.springlearn.slpetclinic.service.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    //@Autowired : Not needed as Spring implicitly takes if the class has single constructor
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Lucky");
        owner1.setLastName("Meghanath");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Ricky");
        owner1.setLastName("Tanush");

        ownerService.save(owner2);

        log.info("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Chinnodu");
        vet1.setLastName("Chalaki");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Peddhodu");
        vet2.setLastName("Penki");

        vetService.save(vet2);

        log.info("Loaded vets...");

    }
}
