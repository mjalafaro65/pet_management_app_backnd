package com.maria.pet_management_app.Pet;

import com.maria.pet_management_app.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true, nullable = false)
    private UUID publicId = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = true)
    private String breed;

    @Column(name = "date_of_birth")
    private LocalDate  dateOfBirth;

    @OneToMany(mappedBy = "pet", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<PetUserPermission> userPermissions = new ArrayList<>();

    public void addUserPermission(User user, PetUserPermission.Role role){
        PetUserPermission permission = new PetUserPermission();
        permission.setPet(this);
        permission.setUser(user);
        permission.setRole(role);
        this.userPermissions.add(permission);
    }





}


