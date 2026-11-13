package com.maria.pet_management_app.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maria.pet_management_app.Pet.PetUserPermission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity //jpa entity
@Table(name="app_users") // not required, defualt is class name
@Getter
@Setter
@NoArgsConstructor()//needed for jpa to create an empty object, can also be a protected(only by class or child) method
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;


    LocalDateTime createdAt;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Manually written to keep the bidirectional User <-> UserProfile link in sync.
// Lombok's @Setter will NOT generate a duplicate setProfile() since this one exists.
// Do not remove — deleting this would break cascade save/orphanRemoval behavior.
    public void setProfile(UserProfile profile) {
        if (profile == null) {
            if (this.profile != null) {
                this.profile.setUser(null);
            }
        } else {
            profile.setUser(this);
        }
        this.profile = profile;
    }

//    @Enumerated(EnumType.STRING)
//    private AuthProvider provider = AuthProvider.LOCAL;
//
//    public enum AuthProvider {
//        LOCAL, GOOGLE
//    }



    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserProfile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PetUserPermission> petPermissions = new ArrayList<>();
}
