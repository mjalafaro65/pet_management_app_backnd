package com.maria.pet_management_app.Schedule;


import com.maria.pet_management_app.Pet.Pet;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DefaultScheduleService {

    private final ScheduleRepository scheduleRepository;


    public DefaultScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduledItem> createDefaultPetSchedule(Pet pet){
        List<ScheduledItem> tasks =new ArrayList<>();

        if(pet.getSpecies()==null){
            return tasks;
        }

        String specie= pet.getSpecies().trim().toLowerCase();
        switch (specie){
            case "dog":
                tasks.add(new ScheduledItem(null, "Morning Walk", LocalTime.of(7,30), pet));
                tasks.add(new ScheduledItem(null, "Morning Feeding" , LocalTime.of(8,30), pet));
                tasks.add(new ScheduledItem(null, "Evening Walk", LocalTime.of(18,0), pet));
                tasks.add(new ScheduledItem(null, "Late Feeding ", LocalTime.of(18,30), pet));


                break;

            case "cat":
                tasks.add(new ScheduledItem(null, "Morning Feeding" , LocalTime.of(8,30), pet));
                tasks.add(new ScheduledItem(null, "Litter Food Clean", LocalTime.of(18,0), pet));
                tasks.add(new ScheduledItem(null, "Evening Feeding ", LocalTime.of(18,30), pet));
                break;
            default:
                tasks.add(new ScheduledItem(null, "Feeding" , LocalTime.of(8,30), pet));

                break;
        }

        return scheduleRepository.saveAll(tasks);



    }


}
