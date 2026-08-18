package com.maria.pet_management_app.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name must be under 50 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name must be under 50 characters")
        String lastName,

        @Pattern(regexp = "^\\+?[0-9\\- ]{7,15}$", message = "Invalid phone number format")
        String phoneNumber,

        @Pattern(regexp = "^(https?://).*$", message = "Avatar URL must be a valid URL")
        String avatarUrl
) {}