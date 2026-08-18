package com.maria.pet_management_app.auth;

import com.maria.pet_management_app.User.User;
import com.maria.pet_management_app.User.UserProfileResponse;
import com.maria.pet_management_app.User.UserRegisterRequest;
import com.maria.pet_management_app.User.UserService;
import com.maria.pet_management_app.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        //authenticate psswd and email with spring security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        String token= jwtUtil.generateToken(request.email());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser( @Valid @RequestBody UserRegisterRequest request) {
        UserProfileResponse registeredUser= userService.registerUser(request);

        String token= jwtUtil.generateToken(registeredUser.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

}
