package com.maria.pet_management_app.User;
import com.maria.pet_management_app.User.DTOs.AuthRequest;
import com.maria.pet_management_app.User.DTOs.UpdateProfileRequest;
import com.maria.pet_management_app.User.DTOs.UserProfileResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
//constructor for userep and password encoder
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //Constructor Injection better than field
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UserProfileResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                //user-> UserProfileResponse.from(user) : like in react
                .map(UserProfileResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
        return UserProfileResponse.from(user);
    }

    @Transactional
    public UserProfileResponse registerUser(@Valid AuthRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = new User(request.email(), encodedPassword);
        User savedUser = userRepository.save(user);

        return UserProfileResponse.from(savedUser);
    }

    @Transactional
    public UserProfileResponse updateUserProfile(Long id, UpdateProfileRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        UserProfile profile = user.getProfile();
        if (profile == null) {
            profile = new UserProfile();
        }

        profile.setFirstName(request.firstName());
        profile.setLastName(request.lastName());
        profile.setPhoneNumber(request.phoneNumber());
        profile.setAvatarUrl(request.avatarUrl());

        user.setProfile(profile); // Triggers JPA cascade save
        User updatedUser = userRepository.save(user);

        return UserProfileResponse.from(updatedUser);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        return UserProfileResponse.from(user);
    }
}