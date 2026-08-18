package com.maria.pet_management_app.Pet;

import com.maria.pet_management_app.User.User;
import com.maria.pet_management_app.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PetResponse> getAllPets(){
        return petRepository.findAll()
                .stream()
                .map(PetResponse::from)
                .toList();
    }

    @Transactional()
    public PetResponse registerPet(PetRegisterRequest request, Long userId){

//        Optional<User> user =userRepository.findById(userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));

        Pet pet=new Pet();
        pet.setName(request.name());
        pet.setSpecies(request.species());
        pet.setBreed(request.breed());
        pet.setDateOfBirth(request.dateOfBirth());

        Pet savedPet = petRepository.save(pet);

        PetUserPermission permission= new PetUserPermission();
        permission.setPet(savedPet);
        permission.setUser(user);
        permission.setRole(PetUserPermission.Role.OWNER);

        return PetResponse.from(savedPet);
    }







}
