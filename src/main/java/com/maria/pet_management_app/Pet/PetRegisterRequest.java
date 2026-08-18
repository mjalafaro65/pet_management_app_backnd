package com.maria.pet_management_app.Pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record PetRegisterRequest (
        @NotBlank(message = "Pet name is required")
        String name,

        @NotBlank(message = "Species is required")
        String species,
        String breed,


        @NotNull(message = "Date of birth is required")
        @PastOrPresent(message = "Date of birth cannot be in the future")
        LocalDate dateOfBirth


){

}
