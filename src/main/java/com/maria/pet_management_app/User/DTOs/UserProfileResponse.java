package com.maria.pet_management_app.User.DTOs;

import com.maria.pet_management_app.User.User;
import com.maria.pet_management_app.User.UserProfile;

public record UserProfileResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String avatarUrl
) {
    public static UserProfileResponse from(User user) {
        UserProfile profile = user.getProfile();
        return new UserProfileResponse(
                user.getId(),
                user.getEmail(),
                profile != null ? profile.getFirstName() : null,
                profile != null ? profile.getLastName() : null,
                profile != null ? profile.getPhoneNumber() : null,
                profile != null ? profile.getAvatarUrl() : null
        );
    }
}