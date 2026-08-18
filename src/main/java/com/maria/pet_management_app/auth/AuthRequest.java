package com.maria.pet_management_app.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest (
    @NotBlank(message="Email is required")
    @Email(message="Invalid Email Format")
    String email,

    @NotBlank(message = "Password required")
    String password

){}
