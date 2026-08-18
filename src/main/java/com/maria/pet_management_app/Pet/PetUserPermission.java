package com.maria.pet_management_app.Pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maria.pet_management_app.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pet_user_permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetUserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // link to the Pet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @JsonIgnore // Avoid circular JSON references
    private Pet pet;

    // link to the User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // The access level role (e.g., OWNER, CARETAKER, VET)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        OWNER,
        CARETAKER,
        VET
    }
}