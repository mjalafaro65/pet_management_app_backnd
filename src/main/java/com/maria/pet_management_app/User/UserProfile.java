package com.maria.pet_management_app.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "user_profile")
@NoArgsConstructor
@Getter
@Setter
public class UserProfile {
    @Id
    private long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatarUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;


}
