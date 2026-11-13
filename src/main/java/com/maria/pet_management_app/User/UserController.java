package com.maria.pet_management_app.User;

import com.maria.pet_management_app.User.DTOs.UpdateProfileRequest;
import com.maria.pet_management_app.User.DTOs.UserProfileResponse;
import com.maria.pet_management_app.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private  UserService userService;

    // Constructor Injection
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public List<UserProfileResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserProfileResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @PutMapping("/profile")
    public UserProfileResponse updateProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateProfileRequest request) {

        CustomUserDetails userDetails=(CustomUserDetails) authentication.getPrincipal();


        return userService.updateUserProfile(userDetails.getId(), request);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UserProfileResponse response = userService.getProfile(userDetails.getId());

        return ResponseEntity.ok(response);
    }
}