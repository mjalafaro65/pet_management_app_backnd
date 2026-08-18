package com.maria.pet_management_app.Pet;

import com.maria.pet_management_app.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping
    public List<PetResponse> getAllPets(){
        return petService.getAllPets();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse registerPet(@Valid @RequestBody PetRegisterRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long currentUserId = userDetails.getUserId();
        return petService.registerPet(request, currentUserId);
    }



}
