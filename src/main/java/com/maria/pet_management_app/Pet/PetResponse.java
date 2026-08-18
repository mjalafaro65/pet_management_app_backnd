package com.maria.pet_management_app.Pet;

import java.time.LocalDate;

public record PetResponse(
        Long id,
        String name,
        String species,
        String breed,
        LocalDate dateOfBirth
) {
    public static PetResponse from(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getDateOfBirth()
                // userPermissions is intentionally left out!
        );
    }
}